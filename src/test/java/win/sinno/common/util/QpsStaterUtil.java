package win.sinno.common.util;

import java.util.Random;
import org.junit.Test;
import win.sinno.common.util.QpsStater.QpsMessageListener;

/**
 * win.sinno.common.util.QpsStaterUtil
 *
 * @author chenlizhong@qipeng.com
 * @date 2018/8/15
 */
public class QpsStaterUtil {

  @Test
  public void test() throws InterruptedException {
    QpsMessageListener qml = new QpsMessageListener() {
      @Override
      public void receive(String name, Long ts, Integer qps) {
        System.out.println("[" + name + "] " + ts + "," + qps);
      }
    };
    QpsStater qpsStater = new QpsStater(qml);
//    QpsStater qpsStater = new QpsStater();
    Random random = new Random();
    for (int i = 1; i < 10000; i++) {
      qpsStater.add(random.nextInt(100));
      Thread.sleep(230l);
    }

  }

}
