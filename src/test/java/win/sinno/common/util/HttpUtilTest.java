package win.sinno.common.util;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * http util test
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/7/19 17:47
 */
public class HttpUtilTest {

    @Test
    public void testEmail() throws IOException {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("to", "admin@chenlizhong.cn"));
        nvps.add(new BasicNameValuePair("title", "邮件主题测试"));
        nvps.add(new BasicNameValuePair("content", "内容3erdckmj"));

        System.out.println(HttpUtil.post("http://localhost:8080/mail/send", nvps));

    }

}
