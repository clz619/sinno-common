package win.sinno.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

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

  public static final long ONE_SECOND_MILLIS = 1000l;

  public static final long ONE_MINUTE_MILLIS = 1000l * 60;

  public static final long ONE_HOUR_MILLIS = 1000l * 60 * 60;

  public static final long ONE_DAY_MILLIS = 1000l * 60 * 60 * 24;

  public static final long ONE_WEEK_MILLIS = 1000l * 60 * 60 * 24 * 7;

  private static final Map<String, DateUnit> DATE_UNIT_MAP = new HashMap<>();

  static {
    DATE_UNIT_MAP.put(DateUnit.SECOND.getUnit(), DateUnit.SECOND);
    DATE_UNIT_MAP.put(DateUnit.MINUTE.getUnit(), DateUnit.MINUTE);
    DATE_UNIT_MAP.put(DateUnit.HOUR.getUnit(), DateUnit.HOUR);
    DATE_UNIT_MAP.put(DateUnit.DAY.getUnit(), DateUnit.DAY);
    DATE_UNIT_MAP.put(DateUnit.WEEK.getUnit(), DateUnit.WEEK);
  }

  /**
   * 时间单位
   */
  public enum DateUnit {

    /**
     * s.秒
     */
    SECOND("s", "秒", ONE_SECOND_MILLIS),
    /**
     * m.分
     */
    MINUTE("m", "分", ONE_MINUTE_MILLIS),
    /**
     * h.时
     */
    HOUR("h", "时", ONE_HOUR_MILLIS),
    /**
     * d.天
     */
    DAY("d", "天", ONE_DAY_MILLIS),
    /**
     * w.周
     */
    WEEK("w", "周", ONE_WEEK_MILLIS),;

    DateUnit(String unit, String descr, Long millis) {
      this.unit = unit;
      this.descr = descr;
      this.millis = millis;
    }

    private String unit;

    private String descr;

    private Long millis;


    public String getUnit() {
      return unit;
    }

    public String getDescr() {
      return descr;
    }

    public Long getMillis() {
      return millis;
    }
  }

  /**
   * 获取间隔天数
   */
  public static long getIntervalDay(Long beginTs, Long endTs) {
    //间隔毫秒数
    long intervalMillis = endTs - beginTs;
    return intervalMillis / ONE_DAY_MILLIS;
  }

  /**
   * 将时间戳进行格式化
   */
  public static String format(Long ts, String pattern) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    return simpleDateFormat.format(ts);
  }

  /**
   * 将字符串格式化为时间戳
   */
  public static Long format2TimeMilles(String timeStr, String pattern) throws ParseException {
    Date date = format2Date(timeStr, pattern);
    return date.getTime();
  }

  public static Date format2Date(String timeStr, String pattern) throws ParseException {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    return simpleDateFormat.parse(timeStr);
  }

  /**
   * 转换成时间戳
   *
   * @return 符合规范，如1s、1m、1h、1d、1w返回正常时间戳
   * 不符合规范的，如12ss返回null
   */
  public static Long format2Milles(String timeWithUnit) {

    if (StringUtils.isBlank(timeWithUnit)) {
      return null;
    }

    String lowerTime = timeWithUnit.trim().toLowerCase();
    int len = lowerTime.length();

    String valStr = lowerTime.substring(0, len - 1);
    String unitStr = lowerTime.substring(len - 1);

    Long timeVal = null;
    try {
      timeVal = Long.valueOf(valStr);
    } catch (Exception e) {
      return null;
    }

    DateUnit dateUnit = DATE_UNIT_MAP.get(unitStr);
    if (dateUnit == null) {
      return null;
    }

    return timeVal * dateUnit.getMillis();
  }

  public static Long getNowTsWithoutMillis() {
    return System.currentTimeMillis() / 1000 * 1000;
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
