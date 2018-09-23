package win.sinno.common.util;

import org.junit.Test;

/**
 * time util
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017-05-24 10:50.
 */
public class TimeTracerTest {

  @Test
  public void test() throws InterruptedException {

    TimeTracer timeTracer = TimeTracer.newInstance("sdfdstgf23");

    timeTracer.trace("frist");

    Thread.sleep(1000l);

    timeTracer.trace("hhhh");

    Thread.sleep(2000l);

    timeTracer.trace("finish");

    timeTracer.end();

    System.out.println(timeTracer.report());
  }

  @Test
  public void nanoTest() throws InterruptedException {

    System.out.println(System.currentTimeMillis());
    System.out.println(System.nanoTime());
    Thread.sleep(1l);
    System.out.println(System.nanoTime());
    Thread.sleep(8l);
    System.out.println(System.nanoTime());
    Thread.sleep(1l);
    System.out.println(System.nanoTime());
  }
}
