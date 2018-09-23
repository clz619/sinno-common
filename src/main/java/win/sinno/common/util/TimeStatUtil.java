package win.sinno.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * win.sinno.common.util.TimeStatUtil
 *
 * @author chenlizhong@qipeng.com
 * @date 2018/8/17
 */
public class TimeStatUtil {

  public Map<String, AtomicLong> useNanoTsStatMap = new HashMap<>();

  private static ThreadLocal<String> tagTL = new ThreadLocal<>();
  private static ThreadLocal<Long> pointTsTL = new ThreadLocal<>();

  public void begin(String tag) {
    tagTL.set(tag);
    pointTsTL.set(System.nanoTime());
  }

  public void end() {
    if (pointTsTL.get() != null) {
      long useNanoTs = System.nanoTime() - pointTsTL.get();
      String tag = tagTL.get();
      if (tag == null) {
        tag = "default";
      }

      AtomicLong al = useNanoTsStatMap.get(tag);

      if (al == null) {

        synchronized (this) {
          al = useNanoTsStatMap.get(tag);

          if (al == null) {
            al = new AtomicLong();
            useNanoTsStatMap.put(tag, al);
          }

        }
      }
      al.addAndGet(useNanoTs);
    }
  }

  public String statDetail() {
    return useNanoTsStatMap.toString();
  }


}
