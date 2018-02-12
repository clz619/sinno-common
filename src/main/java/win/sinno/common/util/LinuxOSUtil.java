package win.sinno.common.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * win.sinno.common.util.LinuxOSUtil
 *
 * @author chenlizhong@qipeng.com
 * @date 2018/2/12
 */
public class LinuxOSUtil {


  /**
   * cpu指标
   */
  public interface CpuStat {

    String USER = "user";
    String SYSTEM = "system";
    String NICE = "nice";
    String IDLE = "idle";
    String IOWAIT = "iowait";
    String IRQ = "irq";
    String SOFTIRQ = "softirq";
    String STEALSTONLEN = "stealstolen";
  }

  /**
   * 功能：可用磁盘 单位 kb
   */
  public static long freeSpaceKb(String path) {
    if (StringUtils.isBlank(path)) {
      path = "/";
    }
    try {
      return FileSystemUtils.freeSpaceKb(path);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0l;
  }

  /**
   * 功能：获取Linux系统cpu使用率 30%
   */
  public static int cpuUsage() {
    try {
      Map<String, Long> map1 = cpuinfo();
      Thread.sleep(5000l);
      Map<String, Long> map2 = cpuinfo();

      long user1 = map1.get(CpuStat.USER);
      long nice1 = map1.get(CpuStat.NICE);
      long system1 = map1.get(CpuStat.SYSTEM);
      long idle1 = map1.get(CpuStat.IDLE);

      long user2 = map2.get(CpuStat.USER);
      long nice2 = map2.get(CpuStat.NICE);
      long system2 = map2.get(CpuStat.SYSTEM);
      long idle2 = map2.get(CpuStat.IDLE);

      long total1 = user1 + system1 + nice1;
      long total2 = user2 + system2 + nice2;
      float total = total2 - total1;

      long totalIdle1 = user1 + nice1 + system1 + idle1;
      long totalIdle2 = user2 + nice2 + system2 + idle2;
      float totalidle = totalIdle2 - totalIdle1;

      float cpusage = (total / totalidle) * 100;
      return (int) cpusage;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  /**
   * 功能：CPU使用信息
   */
  public static Map<String, Long> cpuinfo() {
    InputStreamReader inputs = null;
    BufferedReader buffer = null;
    Map<String, Long> map = new HashMap<String, Long>();
    try {
      inputs = new InputStreamReader(new FileInputStream("/proc/stat"));
      buffer = new BufferedReader(inputs);
      String line = "";
      while (true) {
        line = buffer.readLine();
        if (line == null) {
          break;
        }
        if (line.startsWith("cpu")) {
          StringTokenizer tokenizer = new StringTokenizer(line);
          List<String> temp = new ArrayList<String>();

          while (tokenizer.hasMoreElements()) {
            String value = tokenizer.nextToken();
            temp.add(value);
          }

          map.put(CpuStat.USER, Long.valueOf(temp.get(1)));
          map.put(CpuStat.NICE, Long.valueOf(temp.get(2)));
          map.put(CpuStat.SYSTEM, Long.valueOf(temp.get(3)));
          map.put(CpuStat.IDLE, Long.valueOf(temp.get(4)));
          map.put(CpuStat.IOWAIT, Long.valueOf(temp.get(5)));
          map.put(CpuStat.IRQ, Long.valueOf(temp.get(6)));
          map.put(CpuStat.SOFTIRQ, Long.valueOf(temp.get(7)));
          map.put(CpuStat.STEALSTONLEN, Long.valueOf(temp.get(8)));
          break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        buffer.close();
        inputs.close();
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }
    return map;
  }

  public static Map<String, Long> memInfo() {
    Map<String, Long> map = new HashMap<String, Long>();
    InputStreamReader inputs = null;
    BufferedReader buffer = null;
    try {
      inputs = new InputStreamReader(new FileInputStream("/proc/meminfo"));
      buffer = new BufferedReader(inputs);
      String line = "";
      while (true) {
        line = buffer.readLine();
        if (line == null) {
          break;
        }
        int beginIndex = 0;
        int endIndex = line.indexOf(":");
        if (endIndex != -1) {
          String key = line.substring(beginIndex, endIndex);
          beginIndex = endIndex + 1;
          endIndex = line.length();
          String memory = line.substring(beginIndex, endIndex);
          String value = memory.replace("kB", "").trim();

          map.put(key, Long.valueOf(value));
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        buffer.close();
        inputs.close();
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }
    return map;
  }

  /**
   * 功能：内存使用率 82%
   */
  public static int memoryUsage() {
    try {
      Map<String, Long> map = memInfo();
      long memTotal = Long.parseLong(map.get(MemInfo.MEM_TOTAL).toString());
      long memFree = Long.parseLong(map.get(MemInfo.MEM_FREE).toString());

      long buffers = Long.parseLong(map.get(MemInfo.BUFFERS).toString());
      long cached = Long.parseLong(map.get(MemInfo.CACHED).toString());

      long memused = memTotal - memFree;

      double usage = (double) (memused - buffers - cached) / memTotal * 100;
      return (int) usage;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return 0;
  }

  public interface MemInfo {

    String MEM_TOTAL = "MemTotal";
    String MEM_FREE = "MemFree";
    String MEM_AVAILABLE = "MemAvailable";
    String BUFFERS = "Buffers";
    String CACHED = "Cached";
  }
}
