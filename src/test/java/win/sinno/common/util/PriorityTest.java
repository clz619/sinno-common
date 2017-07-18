package win.sinno.common.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 算术符优先级测试
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/6/21 16:14
 */
public class PriorityTest {

    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        list.add("asd");
        list.add("123");

        System.out.println(list + "===" + list != null + "-------" + (list.size() > 0));
    }
}
