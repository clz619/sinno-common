package win.sinno.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/6/6 17:21
 */
public class MethodUtil {

    public static final String getSetterName(String fieldName) {
        if (StringUtils.isBlank(fieldName)) {
            return null;
        }

        String upperFc = fieldName.substring(0, 1).toUpperCase();
        String setterName = "set" + upperFc;

        if (fieldName.length() > 1) {
            setterName += fieldName.substring(1, fieldName.length());
        }

        return setterName;
    }
}
