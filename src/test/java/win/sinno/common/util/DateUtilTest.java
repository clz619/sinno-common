package win.sinno.common.util;

import java.text.ParseException;
import java.util.Date;
import org.junit.Test;

/**
 * Date util test
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/17 上午11:55
 */
public class DateUtilTest {

  @Test
  public void testFormate() {
    String t = "1702141454";
    try {
      Long ts = DateUtil.format2TimeMilles(t, "yyMMddHHmm");
      System.out.println(new Date(ts));
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testFormatMillis() {
    String[] array = {"2m", "34s", "4h", "1d"};

    for (String s : array) {
      System.out.println(s + " : " + DateUtil.format2Milles(s));
    }
  }

  @Test
  public void testDateRange() {

    Date date = new Date();
    int m = date.getMinutes();
    m = m - m % 10;
    date.setMinutes(m);
    date.setSeconds(0);
    System.out.println(date);
    System.out.println(date.getTime());

  }

  @Test
  public void testTs() {
    String bts = "2018-07-09 00:00:00";
    String ets = "2018-07-12 15:00:00";
    String pattern = "yyyy-MM-dd HH:mm:ss";
    try {
      Long bbts = DateUtil.format2TimeMilles(bts, pattern);
      Long eets = DateUtil.format2TimeMilles(ets, pattern);
      System.out.println(bbts);
      System.out.println(eets);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }
}
