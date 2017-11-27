package win.sinno.common.util;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.junit.Test;
import win.sinno.common.util.ShellExecUtil.ShellExecRet;

/**
 * win.sinno.common.util.ShellExecUtilTest
 *
 * @author admin@chenlizhong.cn
 * @date 2017/11/27
 */
public class ShellExecUtilTest {


  @Test
  public void test() throws InterruptedException, ExecutionException, IOException {
    /**
     *
     ShellExecRet{cmd='ssh admin@test 'ls ~/logs/status-2017-11-25-1.log'', exitVal=255, processMsg='null', processErrMsg='ssh: Could not resolve hostname test: nodename nor servname provided, or not known
     '}
     ShellExecRet{cmd='ls', exitVal=0, processMsg='README.md
     libs
     package
     pom.xml
     sinno-common.iml
     src
     target
     ', processErrMsg='null'}
     */
    String cmd = "ssh admin@test 'ls ~/logs/status-2017-11-25-1.log'";

    ShellExecRet ret = ShellExecUtil.exec(cmd);

    System.out.println(ret);

    ret = ShellExecUtil.exec("ls");

    System.out.println(ret);
  }

}
