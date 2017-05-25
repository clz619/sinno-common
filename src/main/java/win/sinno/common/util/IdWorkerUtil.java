package win.sinno.common.util;

/**
 * id generate
 * 42bit,139year
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2016-04-15 11:59
 */
public final class IdWorkerUtil {

    // 2017-04-10 00:00:00
    private final static long twepoch = 1491753600000L;

    private final static long workerIdBits = 8L;

    private final static long sequenceBits = 14L;

    public final static long maxWorkerId = -1L ^ -1L << workerIdBits;

    public final static long sequenceMask = -1L ^ -1L << sequenceBits;

    private final static long workerIdShift = sequenceBits;

    private final static long timestampLeftShift = sequenceBits + workerIdBits;

    private final long workerId;

    private long sequence = 0L;

    private long lastTimestamp = -1L;

    public IdWorkerUtil(final long workerId) {
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

        //同步时间 或 修改时间，可能造成机器当前 比上一次的时间戳还要早，这样可能会产生重复的ip，这是不允许的
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
     * @return
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }

}
