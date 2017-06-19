package win.sinno.common.util;

/**
 * id generate
 * 42bit,139year
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2016-04-15 11:59
 */
public final class IdWorker {

    // 2017-04-10 00:00:00
    private final static long twepoch = 1491753600000L;

    private final static long workerIdBits = 10L;

    private final static long sequenceBits = 12L;

    public final static long maxWorkerId = -1L ^ -1L << workerIdBits;

    public final static long sequenceMask = -1L ^ -1L << sequenceBits;

    private final static long workerIdShift = sequenceBits;

    private final static long timestampLeftShift = sequenceBits + workerIdBits;

    private final long workerId;

    private long sequence = 0L;

    private long lastTimestamp = -1L;

    public IdWorker(final long workerId) {
        if (workerId > this.maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format(
                    "worker Id can't be greater than %d or less than 0",
                    this.maxWorkerId));
        }
        this.workerId = workerId;
    }

    /**
     * 生成id
     *
     * @return
     */
    public synchronized long nextId() {

        long timestamp = this.timeGen();

        if (this.lastTimestamp == timestamp) {
            this.sequence = (this.sequence + 1) & this.sequenceMask;

            if (this.sequence == 0) {
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }

        // 同步时间 或 修改时间，可能造成机器当前 比上一次的时间戳还要早，这样可能会产生重复的id，这是不允许的
        // 但是若jvm重启过，即lastTimestamp重置了，则也有可能产生和之前产生的相同的id
        if (timestamp < this.lastTimestamp) {
            try {
                throw new Exception(
                        String.format(
                                "Clock moved backwards.  Refusing to generate id for %d milliseconds",
                                this.lastTimestamp - timestamp));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.lastTimestamp = timestamp;

        //生成id
        long nextId = ((timestamp - twepoch << timestampLeftShift))
                | (this.workerId << this.workerIdShift) | (this.sequence);

        return nextId;
    }

    /**
     * @param lastTimestamp
     * @return
     */
    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    /**
     * @return 毫秒级
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }

}
