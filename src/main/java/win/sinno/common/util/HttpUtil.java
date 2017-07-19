package win.sinno.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * http util
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/7/19 17:40
 */
public class HttpUtil {

    public static String post(String url, List<NameValuePair> nvps) throws IOException {

        String ret = null;

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        CloseableHttpResponse response = null;

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

            response = httpclient.execute(httpPost);

            HttpEntity entity = response.getEntity();

            ret = EntityUtils.toString(entity);

            EntityUtils.consume(entity);

        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return ret;
    }
}
