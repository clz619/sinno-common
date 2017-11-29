package win.sinno.common.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * win.sinno.common.util.StringUtilTest
 *
 * @author chenlizhong@qipeng.com
 * @date 2017/10/30
 */
public class StringUtilTest {

  @Test
  public void testJoin() {
    List<String> aa = new ArrayList<String>();
    aa.add("aaa");
    aa.add("bbb");
    System.out.println(StringUtils.join(aa, ","));
  }

  @Test
  public void testS() throws ParseException {
    Date date = DateUtil.format2Date("2017-10-01 00:00:00", "yyyy-MM-dd HH:mm:ss");

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    Date date2 = DateUtil.format2Date("2017-11-11 00:00:00", "yyyy-MM-dd HH:mm:ss");

//    long now = System.currentTimeMillis();
    while (calendar.getTimeInMillis() < date2.getTime()) {
//      calendar.add(Calendar.MONTH, 1);

      String str = "select '" + DateUtil
          .format(calendar.getTimeInMillis(), "yyyy-MM-dd") + "',";
      str += "-sum(fee) from trade where gmtCreate >= '" + DateUtil
          .format(calendar.getTimeInMillis(), "yyyy-MM-dd HH:mm:ss");

      calendar.add(Calendar.DAY_OF_YEAR, 1);
      str = str
          + "' and gmtCreate <  '" + DateUtil
          .format(calendar.getTimeInMillis(), "yyyy-MM-dd HH:mm:ss")
          + "' and fee < 0 and fee_type = 0 and type <= 1 and seller_id < 888888888888888;";

      System.out.println(str);
    }


  }

  @Test
  public void test1_45() {
    System.out.println(1l << 45);

    Double d = -11d;

    System.out.println("aa " + (-d.longValue()) + " bb");
  }

  @Test
  public void test22() {
    long tag = 48381326524418l;
    System.out.println(tag | (1l << 45));

    System.out.println(tag & (1l << 45));

    System.out.println(tag - (1l << 45));

    System.out.println(tag ^ ((1l << 45)));
  }
}
