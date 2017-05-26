package win.sinno.common.util;

import org.junit.Test;

/**
 * random test
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017-05-25 14:32.
 */
public class RandomUtilTest {

    @Test
    public void test() {
        for (int i = 0; i < 20; i++) {
            System.out.println(RandomUtil.random(255));
        }
    }
}
