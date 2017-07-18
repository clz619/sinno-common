package win.sinno.common.util;

import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

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
}
