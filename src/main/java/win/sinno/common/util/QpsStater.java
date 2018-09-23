package win.sinno.common.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * win.sinno.common.util.QpsStater
 *
 * @author chenlizhong@qipeng.com
 * @date 2018/8/15
 */
public class QpsStater {

  // two lock
  private Object[] lock = new Object[]{new Object(), new Object()};

  private RemovalListener<Long, AtomicInteger> removalListener;

  private Cache<Long, AtomicInteger> qpsCache;

  public QpsStater() {
    this(new DefaultQpsMessageListener());
  }

  public QpsStater(final QpsMessageListener qpsMessageListener) {

    this.removalListener = new RemovalListener<Long, AtomicInteger>() {
      @Override
      public void onRemoval(RemovalNotification<Long, AtomicInteger> notification) {
        qpsMessageListener.receive(notification.getKey(), notification.getValue().get());
      }
    };

    qpsCache = CacheBuilder.newBuilder()
        .maximumSize(3)
        .expireAfterWrite(2, TimeUnit.SECONDS)
        .removalListener(removalListener).build();

  }

  public void increment() {
    this.add(1);
  }

  public void add(int delta) {
    Long nowTsWithoutMillis = DateUtil.getNowTsWithoutMillis();

    AtomicInteger at = qpsCache.getIfPresent(nowTsWithoutMillis);

    if (at == null) {
      synchronized (getLock(nowTsWithoutMillis)) {
        at = qpsCache.getIfPresent(nowTsWithoutMillis);
        if (at == null) {
          at = new AtomicInteger();
          qpsCache.put(nowTsWithoutMillis, at);
        }
      }
    }

    at.addAndGet(delta);

  }

  private Object getLock(Long ts) {
    return lock[(int) ((ts / 1000) % 2)];
  }

  public void stop() {
    qpsCache.invalidateAll();
  }

  public interface QpsMessageListener {

    void receive(Long ts, Integer qps);
  }

  public static class DefaultQpsMessageListener implements QpsMessageListener {

    private static final Logger LOG = LoggerFactory.getLogger("qps");

    @Override
    public void receive(Long ts, Integer qps) {
      LOG.info(ts + ":" + qps);
    }
  }
}
