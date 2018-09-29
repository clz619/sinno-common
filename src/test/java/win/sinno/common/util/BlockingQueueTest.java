package win.sinno.common.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.Test;

/**
 * win.sinno.common.util.BlockingQueueTest
 *
 * @author chenlizhong@qipeng.com
 * @date 2018/9/28
 */
public class BlockingQueueTest {

  @Test
  public void test() throws InterruptedException {

    final BlockingQueue<Integer> bq = new LinkedBlockingQueue<Integer>();

    new Thread() {
      @Override
      public void run() {
        bq.add(1);

        try {
          Thread.sleep(3000l);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        bq.add(2);
      }
    }.start();

    new Thread() {
      @Override
      public void run() {
        while (true) {
          try {
            Integer val = bq.take();
            System.out.println(System.currentTimeMillis() + ":" + val);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }.start();

    Thread.sleep(10000l);
  }
}
