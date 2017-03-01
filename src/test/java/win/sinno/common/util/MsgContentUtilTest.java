package win.sinno.common.util;

import org.junit.Test;

/**
 * TODO
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/28 上午11:29
 */
public class MsgContentUtilTest {

    @Test
    public void test() {
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


        System.out.println(Long.MAX_VALUE);
    }
}
