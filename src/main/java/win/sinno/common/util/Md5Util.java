package win.sinno.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

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
     * char int map
     */
    private static Map<Character, Integer> CHAR_INT_MAP = new HashMap<Character, Integer>();

    static {
        CHAR_INT_MAP.put('0', 0);
        CHAR_INT_MAP.put('1', 1);
        CHAR_INT_MAP.put('2', 2);
        CHAR_INT_MAP.put('3', 3);
        CHAR_INT_MAP.put('4', 4);
        CHAR_INT_MAP.put('5', 5);
        CHAR_INT_MAP.put('6', 6);
        CHAR_INT_MAP.put('7', 7);
        CHAR_INT_MAP.put('8', 8);
        CHAR_INT_MAP.put('9', 9);
        CHAR_INT_MAP.put('A', 10);
        CHAR_INT_MAP.put('B', 11);
        CHAR_INT_MAP.put('C', 12);
        CHAR_INT_MAP.put('D', 13);
        CHAR_INT_MAP.put('E', 14);
        CHAR_INT_MAP.put('F', 15);
    }

    /**
     * md5 byte
     *
     * @param s
     * @return
     */
    public static final byte[] MD5Byte(String s) {
        try {
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            return mdInst.digest(s.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * md5 byte
     *
     * @param bytes
     * @return
     */
    public static final byte[] MD5Byte(byte[] bytes) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            return messageDigest.digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

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

        try {
            byte[] btInput = s.getBytes();

            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            // 获得密文
            byte[] md = mdInst.digest(btInput);

            return MD5Byte2Str(md);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public final static byte[] MD5Str2Byte(String md5Str) {
        if (md5Str == null || md5Str.length() != 32) {
            return null;
        }

        // 2 up
        String upMd5Str = md5Str.toUpperCase();

        byte[] bytes = new byte[16];

        for (int i = 0, pos = 0; i < 32; i = i + 2, pos++) {
            bytes[pos] = (byte) ((CHAR_INT_MAP.get(upMd5Str.charAt(i)) << 4) | CHAR_INT_MAP.get(upMd5Str.charAt(i + 1)));
        }

        return bytes;
    }

    public static final String MD5Byte2Str(byte[] bytes) {
        // 把密文转换成十六进制的字符串形式
        int j = bytes.length;

        char str[] = new char[j * 2];

        int k = 0;

        for (int i = 0; i < j; i++) {

            byte byte0 = bytes[i];

            str[k++] = UPPER_HEX_DIGITS[byte0 >>> 4 & 0xf];
            str[k++] = UPPER_HEX_DIGITS[byte0 & 0xf];
        }

        return new String(str);
    }

}
