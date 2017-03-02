package win.sinno.common.util;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * TODO
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/28 上午11:29
 */
public class MsgContentUtilTest {

    @Test
    public void test() throws UnsupportedEncodingException {
        byte[] bytes = MsgContentUtil.formatMsg("a", 8);
        System.out.println(bytes.length);
        bytes = MsgContentUtil.formatMsg("啊", 8);
        System.out.println(bytes.length);

        bytes = MsgContentUtil.formatMsg("a", 15);
        System.out.println(bytes.length);
        bytes = MsgContentUtil.formatMsg("啊", 15);
        System.out.println(bytes.length);

        bytes = MsgContentUtil.formatMsg("a", 0);
        System.out.println(bytes.length);
        bytes = MsgContentUtil.formatMsg("啊", 0);
        System.out.println(bytes.length);
        bytes = MsgContentUtil.formatMsg("a", 0);
        System.out.println(MsgContentUtil.formatMsg(bytes));

        byte[] bs = {89, 26, 92, 17, -108, -79, -1, 31};

        System.out.println(new String(bs, "iso-10646-ucs-2"));

        System.out.println(Long.MAX_VALUE);
    }
}
