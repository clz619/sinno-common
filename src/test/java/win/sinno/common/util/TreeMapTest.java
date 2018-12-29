package win.sinno.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.junit.Test;

/**
 * win.sinno.common.util.TreeMapTest
 *
 * @author chenlizhong@qipeng.com
 * @date 2018/10/24
 */
public class TreeMapTest {

  private static final TreeMap<Long, Integer> DEVEL_LEVEL_TABLE = new TreeMap<Long, Integer>();
  private static final String LDEVEL_STRING =
      "10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h 4h 6h 8h 10h 12h 24h 36h 48h 72h";

  private static final long MIN_INTERVAL = 10 * 1000;// 最小间隔10ms
  private static final long MIN_ACCEPT_INTERVAL = 30 * 1000; // 最小可接受误差 30ms

  public static void initDelayLevel() {
    HashMap<String, Long> timeUnitTable = new HashMap<String, Long>();
    timeUnitTable.put("s", 1000L);
    timeUnitTable.put("m", 1000L * 60);
    timeUnitTable.put("h", 1000L * 60 * 60);
    timeUnitTable.put("d", 1000L * 60 * 60 * 24);

    String[] levelArray = LDEVEL_STRING.split(" ");
    for (int i = 0; i < levelArray.length; i++) {
      String value = levelArray[i];
      String ch = value.substring(value.length() - 1);
      Long tu = timeUnitTable.get(ch);

      int level = i + 1;
      long num = Long.parseLong(value.substring(0, value.length() - 1));
      long delayTimeMillis = tu * num;
      DEVEL_LEVEL_TABLE.put(delayTimeMillis, level);
    }
  }

  public static int getDelayLevel(String msgId, long delayTime) {
    int delayLevel = 1;
    long dif = delayTime - System.currentTimeMillis();
    if (dif > MIN_INTERVAL) {
      // 大于等于dif的最小值
//      Map.Entry<Long, Integer> maxLevel = DEVEL_LEVEL_TABLE.ceilingEntry(dif);
      Map.Entry<Long, Integer> maxLevel = DEVEL_LEVEL_TABLE.ceilingEntry(dif);
      // 超过最大值
      if (maxLevel == null) {
        delayLevel = DEVEL_LEVEL_TABLE.size();
      } else {
        if (maxLevel.getKey() - dif <= MIN_ACCEPT_INTERVAL) {
          delayLevel = maxLevel.getValue();
        } else {
          // 没找到合适的延时级别，到期后重新计算
          Map.Entry<Long, Integer> minLevel = DEVEL_LEVEL_TABLE.floorEntry(dif);
          delayLevel = minLevel.getValue();
        }
      }
    }

    System.out
        .println(
            "delayLevel id:" + msgId + " " + delayTime + " " + (dif / 1000) + " " + delayLevel);

    return delayLevel;
  }

  static {
    initDelayLevel();
  }

  @Test
  public void test() {
    Map.Entry<Long, Integer> entry = DEVEL_LEVEL_TABLE.floorEntry(61000l);

    System.out.println(entry.getKey() + ":" + entry.getValue());

    entry = DEVEL_LEVEL_TABLE.ceilingEntry(61000l);

    System.out.println(entry.getKey() + ":" + entry.getValue());

    System.out.println(getDelayLevel("1111", System.currentTimeMillis() + 610000l));
  }
}
