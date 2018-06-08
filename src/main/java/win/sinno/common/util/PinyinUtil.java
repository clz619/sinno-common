package win.sinno.common.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;

/**
 * win.sinno.common.util.PinyinUtil
 *
 * @author chenlizhong@qipeng.com
 * @date 2018/6/8
 */
public class PinyinUtil {

  private static final HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();

  static {
    hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
    hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    hanyuPinyinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
  }

  public static String format2PinYin(String chinese) {

    StringBuilder output = new StringBuilder();
    char[] chars = chinese
        .trim()
        .toCharArray();
    for (int i = 0; i < chars.length; i++) {
      try {
        if (chars[i] > 128) {
          String[] temp = PinyinHelper.toHanyuPinyinStringArray(chars[i], hanyuPinyinOutputFormat);
          for (String s : temp) {
            output.append(s);
          }

        } else {
          output.append(chars[i]);
        }
      } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
        badHanyuPinyinOutputFormatCombination.printStackTrace();
      }
    }

    return output.toString();
  }

  public static String format2PinYinFirstChar(String chinese) {

    StringBuilder output = new StringBuilder();
    char[] chars = chinese
        .trim()
        .toCharArray();
    for (int i = 0; i < chars.length; i++) {
      try {
        if (chars[i] > 128) {
          String[] temp = PinyinHelper.toHanyuPinyinStringArray(chars[i], hanyuPinyinOutputFormat);

          if (temp.length > 0) {
            String c = temp[0];
            if (StringUtils.isNotBlank(c)) {
              output.append(c.charAt(0));
            }
          }
        } else {
          output.append(chars[i]);
        }
      } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
        badHanyuPinyinOutputFormatCombination.printStackTrace();
      }
    }

    return output.toString();
  }
}
