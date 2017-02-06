package win.sinno.common.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * run
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/2/6 下午1:52
 */
public class RuntimeUtilTest {

    @Test
    public void testRun() {
        String runName = RuntimeUtil.getRunName();
        String runPid = RuntimeUtil.getRunPid();
        System.out.println(runName);
        System.out.println(runPid);

        Assert.assertTrue(runName.startsWith(runPid));
    }
}
