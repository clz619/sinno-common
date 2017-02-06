package win.sinno.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 时间util
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2016-12-06 15:26
 */
public class DateUtil {

    /**
     * 一天的毫秒时长
     */
    public static final long ONE_DAY_MILLIS = 1000l * 60 * 60 * 24;


    /**
     * 时间戳格式化map
     */
    private static Map<String, SimpleDateFormat> simpleDateFormatMap = new HashMap<String, SimpleDateFormat>();

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
        SimpleDateFormat simpleDateFormat = simpleDateFormatMap.get(pattern);

        if (simpleDateFormat == null) {

            synchronized (simpleDateFormatMap) {

                simpleDateFormat = simpleDateFormatMap.get(pattern);

                if (simpleDateFormat == null) {

                    simpleDateFormat = new SimpleDateFormat(pattern);

                    simpleDateFormatMap.put(pattern, simpleDateFormat);
                }
            }
        }
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
        SimpleDateFormat simpleDateFormat = simpleDateFormatMap.get(pattern);
        if (simpleDateFormat == null) {

            synchronized (simpleDateFormatMap) {

                simpleDateFormat = simpleDateFormatMap.get(pattern);

                if (simpleDateFormat == null) {

                    simpleDateFormat = new SimpleDateFormat(pattern);

                    simpleDateFormatMap.put(pattern, simpleDateFormat);
                }
            }
        }

        Date date = simpleDateFormat.parse(timeStr);
        return date.getTime();
    }

    public static void main(String[] args) {

        long beginTs = 1481003013132l;
        long endTs = 1481003101644l + ONE_DAY_MILLIS + 1;

        long day = DateUtil.getIntervalDay(beginTs, endTs);

        System.out.println(day);
    }

}
