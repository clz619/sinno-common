package win.sinno.common.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 数组工具类
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2016-12-08 15:46
 */
public final class ArrayUtil {

    private ArrayUtil() {
    }

    /**
     * 过滤空字符串
     *
     * @param array
     * @return
     */
    public static String[] filterEmptyStr(String[] array) {

        if (ArrayUtils.isEmpty(array)) {
            return array;
        }

        List<String> strList = new ArrayList<String>();
        for (String str : array) {
            if (StringUtils.isNotBlank(str)) {
                strList.add(str);
            }
        }
        return strList.toArray(new String[0]);
    }

}
