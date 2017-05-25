package win.sinno.common.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * time trace
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017-05-24 10:23.
 */
public class TimeTracer {

    private String identify;

    private int i = 0;

    private AtomicBoolean first = new AtomicBoolean();

    private TimeTuple timeTuple;

    private List<TraceEvent> traceEventList;

    private TimeTracer(String identify) {
        this.identify = identify;
        timeTuple = new TimeTuple();
        traceEventList = new ArrayList<>();
    }

    public static TimeTracer newInstance(String identify) {
        return new TimeTracer(identify);
    }

    public long getBeginTs() {
        return timeTuple.getBeginTs();
    }

    public long getTotalUseTs() {
        return timeTuple.getEndTs() - timeTuple.getBeginTs();
    }

    public long getEndTs() {
        return timeTuple.getEndTs();
    }

    public int getTraceNum() {
        return traceEventList.size();
    }

    public void begin() {
        if (first.compareAndSet(false, true)) {
            timeTuple.setBeginTs(System.currentTimeMillis());
            timeTuple.setNowTs(timeTuple.getBeginTs());
        }
    }

    public synchronized void trace(String msg) {
        begin();

        timeTuple.setPreTs(timeTuple.getNowTs());
        timeTuple.setNowTs(System.currentTimeMillis());
        addTraceEvent(++i, msg, timeTuple.getNowTs() - timeTuple.getPreTs());
    }

    public synchronized void end() {
        timeTuple.setEndTs(timeTuple.getNowTs());
    }

    private void addTraceEvent(int idx, String msg, long useTs) {
        TraceEvent traceEvent = new TraceEvent();
        traceEvent.setIdx(idx);
        traceEvent.setMsg(msg);
        traceEvent.setUseTs(useTs);
        traceEventList.add(traceEvent);
    }

    public String report() {
        Iterator<TraceEvent> it = traceEventList.iterator();
        StringBuilder sb = new StringBuilder();

        sb.append("[identify=");
        sb.append(identify);
        sb.append(", step=");
        sb.append(i);
        sb.append(", totalUseTs=");
        sb.append(getTotalUseTs());
        sb.append("ms");
        sb.append(", beginTs=");
        sb.append(timeTuple.getBeginTs());
        sb.append(" , endTs=");
        sb.append(timeTuple.getEndTs());
        sb.append("]");
        sb.append(" trace detail:[");
        while (it.hasNext()) {
            TraceEvent traceEvent = it.next();
            sb.append(" idx=");
            sb.append(traceEvent.getIdx());
            sb.append(",");
            sb.append(" msg=");
            sb.append(traceEvent.getMsg());
            sb.append(",");
            sb.append(" useTs=");
            sb.append(traceEvent.getUseTs());
            sb.append("ms. ");
        }
        sb.append("]");

        return sb.toString();
    }


    /**
     * 时间元数据
     */
    public static class TimeTuple {

        private long beginTs;

        private long preTs;

        private long nowTs;

        private long endTs;

        public long getBeginTs() {
            return beginTs;
        }

        public void setBeginTs(long beginTs) {
            this.beginTs = beginTs;
        }

        public long getPreTs() {
            return preTs;
        }

        public void setPreTs(long preTs) {
            this.preTs = preTs;
        }

        public long getNowTs() {
            return nowTs;
        }

        public void setNowTs(long nowTs) {
            this.nowTs = nowTs;
        }

        public long getEndTs() {
            return endTs;
        }

        public void setEndTs(long endTs) {
            this.endTs = endTs;
        }

        @Override
        public String toString() {
            return "TimeTuple{" +
                    "beginTs=" + beginTs +
                    ", preTs=" + preTs +
                    ", nowTs=" + nowTs +
                    ", endTs=" + endTs +
                    '}';
        }
    }

    /**
     * 跟踪事件
     */
    public static class TraceEvent {

        private int idx;

        private String msg;

        private long useTs;

        public int getIdx() {
            return idx;
        }

        public void setIdx(int idx) {
            this.idx = idx;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public long getUseTs() {
            return useTs;
        }

        public void setUseTs(long useTs) {
            this.useTs = useTs;
        }

        @Override
        public String toString() {
            return "TraceEvent{" +
                    "idx=" + idx +
                    ", msg='" + msg + '\'' +
                    ", useTs=" + useTs +
                    '}';
        }
    }
}
