package win.sinno.common.util;

/**
 * byte format tool
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/9 上午10:35
 */
public class ByteUtil {

    private static final String[] HEX_CODE = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    private static final String HEX_STR = "0123456789ABCDEF";

    private ByteUtil() {
    }

    //////////byte 2 other///////
    public static String byte2HexString(byte b) {

        int n = b;

        if (n < 0) {
            n = 256 + n;
        }

        int d1 = n / 16;
        int d2 = n % 16;

        return HEX_CODE[d1] + HEX_CODE[d2];
    }

    public static String byteArray2HexString(byte[] bytes) {

        StringBuilder builder = new StringBuilder();

        for (byte b : bytes) {
            builder.append(byte2HexString(b));
        }

        return builder.toString();
    }

    public static Short byte2short(byte[] bytes) {

        if (bytes.length != 2) {
            throw new IllegalArgumentException("bytes length:" + bytes.length + " can not format to short!");
        }

        return (short)
                ((bytes[0] & 0xff) << 8
                        | (bytes[1] & 0xff))
                ;
    }

    public static Short byte2short(byte[] bytes, int offset) {

        if (bytes.length < offset + 2) {
            throw new IllegalArgumentException("bytes (length:" + bytes.length + ") < (offset:" + offset + ")+2 can not format to short!");
        }

        return (short)
                ((bytes[offset] & 0xff) << 8
                        | (bytes[offset + 1] & 0xff)
                )
                ;
    }

    public static Integer byte2int(byte b) {
        return b & 0xff;
    }

    public static Integer byte2int(byte[] bytes) {

        if (bytes.length != 4) {
            throw new IllegalArgumentException("bytes length:" + bytes.length + " can not format to int!");
        }

        //需要将字节符号位算进去，所以所有 byte & 0xff
        return (bytes[0] & 0xff) << 24
                | (bytes[1] & 0xff) << 16
                | (bytes[2] & 0xff) << 8
                | bytes[3] & 0xff
                ;
    }

    /**
     * bytes中下标offset开始4个字节，转换为int
     *
     * @param bytes
     * @param offset
     * @return
     */
    public static Integer byte2int(byte[] bytes, int offset) {

        if (bytes.length < offset + 4) {
            throw new IllegalArgumentException("bytes (length:" + bytes.length + ") < (offset:" + offset + ")+4 can not format to int!");
        }

        return (bytes[offset] & 0xff) << 24
                | (bytes[offset + 1] & 0xff) << 16
                | (bytes[offset + 2] & 0xff) << 8
                | bytes[offset + 3] & 0xff
                ;
    }

    /**
     * one byte 2 int
     *
     * @param bytes
     * @param offset
     * @return
     */
    public static Integer onebyte2int(byte[] bytes, int offset) {
        return byte2int(bytes[offset]);
    }

    public static Long byte2long(byte[] bytes) {

        if (bytes.length != 8) {
            throw new IllegalArgumentException("bytes length:" + bytes.length + " can not format to long!");
        }

        return ((long) (bytes[0] & 0xff)) << 56
                | ((long) (bytes[1] & 0xff)) << 48
                | ((long) (bytes[2] & 0xff)) << 40
                | ((long) (bytes[3] & 0xff)) << 32
                | ((long) (bytes[4] & 0xff)) << 24
                | ((long) (bytes[5] & 0xff)) << 16
                | ((long) (bytes[6] & 0xff)) << 8
                | ((long) (bytes[7] & 0xff))
                ;
    }

    public static Long byte2long(byte[] bytes, int offset) {

        if (bytes.length < offset + 8) {
            throw new IllegalArgumentException("bytes (length:" + bytes.length + ") < (offset:" + offset + ")+8 can not format to long!");
        }

        return ((long) (bytes[offset] & 0xff)) << 56
                | ((long) (bytes[offset + 1] & 0xff)) << 48
                | ((long) (bytes[offset + 2] & 0xff)) << 40
                | ((long) (bytes[offset + 3] & 0xff)) << 32
                | ((long) (bytes[offset + 4] & 0xff)) << 24
                | ((long) (bytes[offset + 5] & 0xff)) << 16
                | ((long) (bytes[offset + 6] & 0xff)) << 8
                | ((long) (bytes[offset + 7] & 0xff))
                ;
    }

    /**
     * byte 2 String.trim()
     * <p>
     * charset=iso-8859-1
     *
     * @param bytes
     * @param offset
     * @param length
     * @return
     */
    public static String byte2String(byte[] bytes, int offset, int length) {
        return byte2String(bytes, offset, length, null);
    }

    /**
     * byte 2 String.trim()
     * <p>
     * msgFormat = 0 , charset name = iso-8859-1
     * msgFormat = 8 , charset name = iso-10646-ucs-2
     * msgFormat = 15 , charset name = gb18030
     * msgFormat = other , charset name = iso-8859-1
     *
     * @param bytes
     * @param offset
     * @param length
     * @param msgFmt
     * @return
     */
    public static String byte2String(byte[] bytes, int offset, int length, Integer msgFmt) {

        byte[] bs = new byte[length];

        //byte copy
        arraycopy(bytes, offset, bs, 0, length);

        String str = MsgContentUtil.formatMsg(bs, msgFmt);
        return (str != null) ? str.trim() : str;
    }

    /**
     * byte array -> md5 string 32位16进制字符串
     *
     * @param bytes
     * @param offset
     * @param length
     * @return
     */
    public static String byte2Md5String(byte[] bytes, int offset, int length) {
        byte[] bs = new byte[length];

        arraycopy(bytes, offset, bs, 0, length);
        return Md5Util.MD5Byte2Str(bs);
    }

    public static String byte2bit(byte b) {

        StringBuilder sb = new StringBuilder();

        sb.append(b >> 7 & 0x1);
        sb.append(b >> 6 & 0x1);
        sb.append(b >> 5 & 0x1);
        sb.append(b >> 4 & 0x1);
        sb.append(b >> 3 & 0x1);
        sb.append(b >> 2 & 0x1);
        sb.append(b >> 1 & 0x1);
        sb.append(b >> 0 & 0x1);

        return sb.toString();
    }

    public static String short2bit(short s) {

        byte[] bs = short2byte(s);

        StringBuilder sb = new StringBuilder();

        for (byte b : bs) {
            sb.append(byte2bit(b));
        }

        return sb.toString();
    }

    public static String int2bit(int i) {

        byte[] bs = int2byte(i);

        StringBuilder sb = new StringBuilder();

        for (byte b : bs) {
            sb.append(byte2bit(b));
        }

        return sb.toString();
    }

    public static String long2bit(long l) {

        byte[] bs = long2byte(l);

        StringBuilder sb = new StringBuilder();

        for (byte b : bs) {
            sb.append(byte2bit(b));
        }

        return sb.toString();
    }


    //////////other 2 byte///////

    public static byte char2Byte(char c) {
        byte b = (byte) HEX_STR.indexOf(c);
        return b;
    }

    public static byte[] short2byte(short s) {

        byte[] bytes = new byte[2];

        bytes[0] = (byte) (s >> 8);
        bytes[1] = (byte) s;
        return bytes;
    }

    public static void short2byte(short s, byte[] buf, int offset) {

        if (buf.length < offset + 2) {
            throw new IllegalArgumentException("byte array buf (length:" + buf.length + ")<(offset:" + offset + ")+2 can not set a short bytes");
        }

        buf[offset] = (byte) (s >> 8);
        buf[offset + 1] = (byte) s;
    }

    public static byte[] int2byte(int i) {

        byte[] bytes = new byte[4];

        bytes[0] = (byte) (i >> 24);
        bytes[1] = (byte) (i >> 16);
        bytes[2] = (byte) (i >> 8);
        bytes[3] = (byte) i;

        return bytes;
    }

    /**
     * 将i转为bytes并将值设置至buf，其实byte下标为offset
     *
     * @param i
     * @param buf
     * @param offset
     */
    public static void int2byte(int i, byte[] buf, int offset) {

        if (buf.length < offset + 4) {
            throw new IllegalArgumentException("byte array buf (length:" + buf.length + ")<(offset:" + offset + ")+4 can not set a int bytes");
        }

        buf[offset] = (byte) (i >> 24);
        buf[offset + 1] = (byte) (i >> 16);
        buf[offset + 2] = (byte) (i >> 8);
        buf[offset + 3] = (byte) i;
    }

    public static byte[] long2byte(long l) {

        byte[] bytes = new byte[8];

        bytes[0] = (byte) (l >> 56);
        bytes[1] = (byte) (l >> 48);
        bytes[2] = (byte) (l >> 40);
        bytes[3] = (byte) (l >> 32);
        bytes[4] = (byte) (l >> 24);
        bytes[5] = (byte) (l >> 16);
        bytes[6] = (byte) (l >> 8);
        bytes[7] = (byte) l;

        return bytes;
    }

    public static void long2byte(long l, byte[] buf, int offset) {

        if (buf.length < offset + 8) {
            throw new IllegalArgumentException("byte array buf (length:" + buf.length + ")<(offset:" + offset + ")+8 can not set a int bytes");
        }

        buf[offset] = (byte) (l >> 56);
        buf[offset + 1] = (byte) (l >> 48);
        buf[offset + 2] = (byte) (l >> 40);
        buf[offset + 3] = (byte) (l >> 32);
        buf[offset + 4] = (byte) (l >> 24);
        buf[offset + 5] = (byte) (l >> 16);
        buf[offset + 6] = (byte) (l >> 8);
        buf[offset + 7] = (byte) l;
    }

    //--array copy

    /**
     * (src bytes) copy to (dest bytes)
     *
     * @param src
     * @param dest
     * @param destOffset
     * @return
     */
    public static Integer arraycopy(byte[] src, byte[] dest, int destOffset) {
        return arraycopy(src, 0, dest, destOffset);
    }

    /**
     * byte array copy
     *
     * @param src
     * @param srcOffset
     * @param dest
     * @param destOffset
     * @return
     */
    public static Integer arraycopy(byte[] src, int srcOffset, byte[] dest, int destOffset) {
        if (src == null || src.length == 0) {
            return 0;
        }

        System.arraycopy(src, srcOffset, dest, destOffset, src.length);

        return src.length;
    }

    /**
     * just like System.arraycopy
     *
     * @param src
     * @param srcOffset
     * @param dest
     * @param destOffset
     * @param len
     * @return
     */
    public static Integer arraycopy(byte[] src, int srcOffset, byte[] dest, int destOffset, int len) {

        if (src == null || src.length == 0) {
            return 0;
        }

        System.arraycopy(src, srcOffset, dest, destOffset, len);

        return len;
    }

    /**
     * string copy 2 byte
     *
     * @param s
     * @param dest
     * @param destOffset
     * @return string byte length
     */
    public static Integer strcopy2byte(String s, byte[] dest, int destOffset) {

        if (s == null) {
            //null 不处理
            return 0;
        }

        byte[] bytes = s.getBytes();

        System.arraycopy(bytes, 0, dest, destOffset, bytes.length);

        return bytes.length;
    }


}
