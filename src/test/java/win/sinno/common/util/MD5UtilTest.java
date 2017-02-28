package win.sinno.common.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * md5 test
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/4 上午9:53
 */
public class MD5UtilTest {

    @Test
    public void testMd5() {
        String s = "123456789";

        Assert.assertEquals(Md5Util.MD5(s), Md5Util.MD5(s));
        Assert.assertEquals(Md5Util.MD5(s, true), Md5Util.MD5(s));
        Assert.assertNotEquals(Md5Util.MD5(s, true), Md5Util.MD5(s, false));
        Assert.assertEquals(Md5Util.MD5(s, true), StringUtils.upperCase(Md5Util.MD5(s)));

        Assert.assertEquals(Md5Util.MD5(s, true).length(), 32);

        System.out.println(Md5Util.MD5(s));
        System.out.println(Md5Util.MD5(s, false));
    }

    @Test
    public void testMd5Str2Byte() {
        String str = "123";
        String a = Md5Util.MD5(str);
        System.out.println(a);
        byte[] bytes = Md5Util.MD5Str2Byte(a);

        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i]);
            System.out.print(" ");
        }

        System.out.println(" ");

        bytes = Md5Util.MD5Byte(str);
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i]);
            System.out.print(" ");
        }

    }
}
