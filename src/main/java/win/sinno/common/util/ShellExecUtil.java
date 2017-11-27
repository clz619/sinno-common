package win.sinno.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * win.sinno.common.util.ShellExecUtil
 *
 * @author : admin@chenlizhong.cn
 * @date 2017/11/27
 */
public final class ShellExecUtil {

  private static final ExecutorService execService = Executors.newCachedThreadPool();

  private ShellExecUtil() {
  }

  public static ShellExecRet exec(String cmd)
      throws IOException, InterruptedException, ExecutionException {

    ShellExecRet shellExecRet = new ShellExecRet(cmd);

    if (StringUtils.isNotBlank(cmd)) {

      String[] command = new String[]{"/bin/sh", "-c", cmd};

      Process process = Runtime.getRuntime().exec(command);

      OutputProcessor out = new OutputProcessor(process.getInputStream());
      OutputProcessor err = new OutputProcessor(process.getErrorStream());

      Future<String> msgFuture = execService.submit(out);
      Future<String> errMsgFuture = execService.submit(err);

      int exitVal = process.waitFor();

      shellExecRet.setExitVal(exitVal);

      if (exitVal == 0) {
        String msg = msgFuture.get();
        shellExecRet.setProcessMsg(msg);
      } else {
        String errMsg = errMsgFuture.get();
        shellExecRet.setProcessErrMsg(errMsg);
      }

      process.destroy();
    }

    return shellExecRet;
  }

  /**
   * shell exec result
   */
  public static class ShellExecRet {

    private String cmd;

    private int exitVal;

    private String processMsg;

    private String processErrMsg;

    public ShellExecRet() {
    }

    public ShellExecRet(String cmd) {
      this.cmd = cmd;
    }

    public String getCmd() {
      return cmd;
    }

    public void setCmd(String cmd) {
      this.cmd = cmd;
    }

    public int getExitVal() {
      return exitVal;
    }

    public void setExitVal(int exitVal) {
      this.exitVal = exitVal;
    }

    public String getProcessMsg() {
      return processMsg;
    }

    public void setProcessMsg(String processMsg) {
      this.processMsg = processMsg;
    }

    public String getProcessErrMsg() {
      return processErrMsg;
    }

    public void setProcessErrMsg(String processErrMsg) {
      this.processErrMsg = processErrMsg;
    }

    @Override
    public String toString() {
      return "ShellExecRet{" +
          "cmd='" + cmd + '\'' +
          ", exitVal=" + exitVal +
          ", processMsg='" + processMsg + '\'' +
          ", processErrMsg='" + processErrMsg + '\'' +
          '}';
    }
  }

  public static class OutputProcessor implements Callable<String> {

    private static final Logger LOG = LoggerFactory.getLogger(OutputProcessor.class);

    private InputStream is;

    public OutputProcessor(InputStream is) {
      this.is = is;
    }


    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {
      StringBuilder sb = new StringBuilder();

      try {
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String line;
        while ((line = br.readLine()) != null) {
          sb.append(line).append("\n");
        }
      } catch (Exception e) {
        LOG.error(e.getMessage(), e);
      }

      return sb.toString();
    }
  }

}
