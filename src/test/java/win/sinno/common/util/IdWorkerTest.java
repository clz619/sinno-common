package win.sinno.common.util;

import org.junit.Test;

/**
 * id worker util
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/4/10 11:24
 */
public class IdWorkerTest {

    @Test
    public void testId() {
        IdWorker idWorker = new IdWorker(1);

        System.out.println(idWorker.nextId());
    }

    @Test
    public void test100Id() {
        IdWorker idWorker = new IdWorker(1);

        for (int i = 0; i < 100; i++) {
            System.out.println(idWorker.nextId());
        }
    }


    @Test
    public void testSpeed() {

        IdWorker idWorker = new IdWorker(1);

        long beginTs = System.currentTimeMillis();

        int c = 0;
        while ((beginTs + 1000) > System.currentTimeMillis()) {
            idWorker.nextId();
            c++;
        }
        // 10210163 1s create 1000w
        System.out.println(c);
    }
}
