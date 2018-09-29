package win.sinno.common.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.lang3.StringUtils;
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

  private long qpsId;

  private static long qpsSeqNumber = 0;

  private String name;

  public QpsStater() {
    this(new DefaultQpsMessageListener());
  }

  private static synchronized long nextQpsId() {
    return ++qpsSeqNumber;
  }

  public QpsStater(final QpsMessageListener qpsMessageListener) {

    this(null, qpsMessageListener);
  }

  public QpsStater(String name, final QpsMessageListener qpsMessageListener) {

    qpsId = nextQpsId();

    if (StringUtils.isNotBlank(name)) {
      this.name = name;
    } else {
      this.name = "qps-" + qpsId;
    }

    this.removalListener = new RemovalListener<Long, AtomicInteger>() {
      @Override
      public void onRemoval(RemovalNotification<Long, AtomicInteger> notification) {
        qpsMessageListener.receive(name, notification.getKey(), notification.getValue().get());
      }
    };

    qpsCache = CacheBuilder.newBuilder().weakKeys()
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

    void receive(String name, Long ts, Integer qps);
  }

  public static class DefaultQpsMessageListener implements QpsMessageListener {

    private static final Logger LOG = LoggerFactory.getLogger("qps");

    private QpsStater qpsStater;

    @Override
    public void receive(Long ts, Integer qps) {
      LOG.info(ts + ":" + qps);
    }

    @Override
    public void receive(String name, Long ts, Integer qps) {
      LOG.info("[" + name + "] " + ts + ":" + qps);
    }
  }
}
