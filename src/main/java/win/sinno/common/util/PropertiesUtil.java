package win.sinno.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * ━━━━━━oooo━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━oooo━━━━━━
 * <p>
 * props util
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/5/2 13:49
 */
public class PropertiesUtil {

    /**
     * load properties from file
     *
     * @param filepath
     * @return
     * @throws IOException
     */
    public static Properties loadFromFile(String filepath) throws IOException {
        InputStream inputStream = new FileInputStream(new File(filepath));
        Properties props = new Properties();
        props.load(inputStream);
        return props;
    }

    /**
     * load properties from resources
     *
     * @return
     */
    public static Properties loadFromResources(String filename) throws IOException {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(filename);

        Properties props = new Properties();
        props.load(inputStream);
        return props;
    }

    /**
     * load properties from url
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static Properties loadFromUrl(String url) throws IOException {
        URL u = new URL(url);
        InputStream inputStream = u.openStream();

        Properties props = new Properties();
        props.load(inputStream);
        return props;
    }
}
