package win.sinno.common.util;

import java.security.MessageDigest;

/**
 * 唯一id生成器
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2016-04-15 17:59
 */
public class Md5Util {

    /**
     * 小写16进制数字
     */
    public static final char[] LOWER_HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 大写16进制数字
     */
    public static final char[] UPPER_HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * md5加密，默认为大写
     *
     * @param s
     * @return
     */
    public final static String MD5(String s) {
        return MD5(s, true);
    }

    /**
     * md5加密，指定大小写
     *
     * @param s
     * @param isUpper
     * @return
     */
    public final static String MD5(String s, boolean isUpper) {

        //设置大小写
        char hexDigits[] = isUpper ? UPPER_HEX_DIGITS : LOWER_HEX_DIGITS;

        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
