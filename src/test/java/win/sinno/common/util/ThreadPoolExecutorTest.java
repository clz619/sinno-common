package win.sinno.common.util;

import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Test;

/**
 * win.sinno.common.util.ThreadPoolExecutorTest
 *
 * @author chenlizhong@qipeng.com
 * @date 2018/9/29
 */
public class ThreadPoolExecutorTest {

  // -536870912
  private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
  private static final int COUNT_BITS = Integer.SIZE - 3;
  private static final int CAPACITY = (1 << COUNT_BITS) - 1;

  // runState is stored in the high-order bits
  private static final int RUNNING = -1 << COUNT_BITS;
  private static final int SHUTDOWN = 0 << COUNT_BITS;
  private static final int STOP = 1 << COUNT_BITS;
  private static final int TIDYING = 2 << COUNT_BITS;
  private static final int TERMINATED = 3 << COUNT_BITS;

  // Packing and unpacking ctl
  private static int runStateOf(int c) {
    return c & ~CAPACITY;
  }

  private static int workerCountOf(int c) {
    return c & CAPACITY;
  }

  private static int ctlOf(int rs, int wc) {
    return rs | wc;
  }

  @Test
  public void testParam() {

    ThreadPoolExecutorTest t = new ThreadPoolExecutorTest();

    System.out.println(t);
  }
}