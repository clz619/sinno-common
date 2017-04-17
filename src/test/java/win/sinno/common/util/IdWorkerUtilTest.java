package win.sinno.common.util;

import org.junit.Test;

/**
 * id worker util
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/4/10 11:24
 */
public class IdWorkerUtilTest {

    @Test
    public void testId() {
        IdWorkerUtil idWorkerUtil = new IdWorkerUtil(1);

        System.out.println(idWorkerUtil.nextId());
    }


    @Test
    public void testSpeed() {

        IdWorkerUtil idWorkerUtil = new IdWorkerUtil(1);

        long beginTs = System.currentTimeMillis();

        int c = 0;
        while ((beginTs + 1000) > System.currentTimeMillis()) {
            idWorkerUtil.nextId();
            c++;
        }
        // 10210163 1s create 1000w
        System.out.println(c);
    }
}
