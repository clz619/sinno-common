package win.sinno.common.util;

import java.util.Random;
import org.junit.Test;

/**
 * win.sinno.common.util.TimeStatUtilTest
 *
 * @author chenlizhong@qipeng.com
 * @date 2018/8/17
 */
public class TimeStatUtilTest {


  @Test
  public void testTsu() throws InterruptedException {
    TimeStatUtil timeStatUtil = new TimeStatUtil();
    Random random = new Random();
    Long bs = System.currentTimeMillis();
    for (int i = 0; i < 10; i++) {
      timeStatUtil.begin("a");
      Thread.sleep(random.nextInt(10));
      timeStatUtil.end();
    }

    for (int i = 0; i < 10; i++) {
      timeStatUtil.begin("c");
      Thread.sleep(random.nextInt(10));
      timeStatUtil.end();
    }
    Long es = System.currentTimeMillis();

    System.out.println(timeStatUtil.statDetail());
    System.out.println(es - bs);

    System.out.println(Long.MAX_VALUE);
  }
}
