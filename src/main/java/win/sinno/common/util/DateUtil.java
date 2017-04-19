package win.sinno.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间util
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2016-12-06 15:26
 */
public final class DateUtil {

    private DateUtil() {
    }

    /**
     * 一天的毫秒时长
     */
    public static final long ONE_DAY_MILLIS = 1000l * 60 * 60 * 24;


    /**
     * 获取间隔天数
     *
     * @param beginTs
     * @param endTs
     * @return
     */
    public static long getIntervalDay(Long beginTs, Long endTs) {
        //间隔毫秒数
        long intervalMillis = endTs - beginTs;

        return intervalMillis / ONE_DAY_MILLIS;
    }

    /**
     * 将时间戳进行格式化
     *
     * @param ts
     * @param pattern
     * @return
     */
    public static String format(Long ts, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.format(ts);
    }

    /**
     * 将字符串格式化为时间戳
     *
     * @param timeStr
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Long format2TimeMilles(String timeStr, String pattern) throws ParseException {
        Date date = format2Date(timeStr, pattern);
        return date.getTime();
    }

    public static Date format2Date(String timeStr, String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.parse(timeStr);
    }

    public static void main(String[] args) throws ParseException {

        long beginTs = 1481003013132l;
        long endTs = 1481003101644l + ONE_DAY_MILLIS + 1;

        long day = DateUtil.getIntervalDay(beginTs, endTs);

        System.out.println(day);

        String ts = "1704171500";
        System.out.println(DateUtil.format2TimeMilles(ts, "yyMMddHHmm"));
    }

}
