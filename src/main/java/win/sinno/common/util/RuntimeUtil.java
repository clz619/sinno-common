package win.sinno.common.util;

import java.lang.management.ManagementFactory;

/**
 * 运行时工具
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/6 下午1:50
 */
public class RuntimeUtil {

    /**
     * @return 2676@lizhongchendeMacBook-Pro.local
     */
    public static String getRunName() {
        return ManagementFactory.getRuntimeMXBean().getName();
    }

    /**
     * runtime.name=2676@lizhongchendeMacBook-Pro.local
     *
     * @return 2676
     */
    public static String getRunPid() {
        return getRunName().split("@")[0];
    }
}
