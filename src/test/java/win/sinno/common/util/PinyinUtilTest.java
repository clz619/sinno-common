package win.sinno.common.util;

import org.junit.Test;

/**
 * win.sinno.common.util.PinyinUtilTest
 *
 * @author chenlizhong@qipeng.com
 * @date 2018/6/8
 */
public class PinyinUtilTest {

  @Test
  public void testPinyin() {

    String chinese = "abc铭佳童话123[{【】旗舰店ert";

    System.out.println(PinyinUtil.format2PinYin(chinese));
    System.out.println(PinyinUtil.format2PinYinFirstChar(chinese));
  }
}
