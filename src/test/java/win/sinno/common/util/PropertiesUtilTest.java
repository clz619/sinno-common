package win.sinno.common.util;

import org.junit.Test;

import java.io.IOException;
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
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/5/2 14:02
 */
public class PropertiesUtilTest {

    @Test
    public void testFromLocalProject() throws IOException {

        Properties properties = PropertiesUtil.loadFromResources("t.properties");
        System.out.println(properties);
        //{password=kjfireg8342d#!, test.username=sinno-----test, username=sinno}

        Properties p2 = PropertiesUtil.loadFromResources("props/t2.properties");
        System.out.println(p2);
        //{password=ferf32e432#29(#!, test.username=sinno-----test, username=sinno-t3}

    }

    @Test
    public void testFromFile() throws IOException {

        String filepath = "/Users/clz/work/git/sinno-common/src/main/resources/t.properties";

        Properties properties = PropertiesUtil.loadFromFile(filepath);
        System.out.println(properties);
    }


}
