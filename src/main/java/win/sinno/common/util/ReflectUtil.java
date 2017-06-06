package win.sinno.common.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/6/6 17:22
 */
public final class ReflectUtil {

    /**
     * 设置属性
     *
     * @param object
     * @param properties
     */
    public static void setFieldWithProps(Object object, Properties properties) {
        Enumeration<Object> keys = properties.keys();

        Object obj = null;
        String key = null;
        Object valObj = null;
        String val = null;

        while (keys.hasMoreElements()) {
            obj = keys.nextElement();

            if (obj != null) {
                key = String.valueOf(obj);
                valObj = properties.get(key);

                if (valObj != null) {
                    val = String.valueOf(valObj);

                    if (StringUtils.isNotBlank(val)) {
                        setFiled(object, key, val);
                    }
                }
            }
        }
    }

    public static void setFiled(Object object, String name, String val) {
        Method[] methods = object.getClass().getDeclaredMethods();
        String targetMethodName = MethodUtil.getSetterName(name);

        for (Method method : methods) {
            String methodName = method.getName();
            Class<?>[] classes = method.getParameterTypes();

            if (targetMethodName.equals(methodName) && classes.length == 1) {

                Class<?> paramClazz = classes[0];
                Object param = null;

                if (String.class == paramClazz) {
                    param = val;
                }

                if (Long.class == paramClazz || long.class == paramClazz) {
                    try {
                        Long l = Long.valueOf(val);
                        param = l;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (Integer.class == paramClazz || int.class == paramClazz) {
                    try {
                        Integer i = Integer.valueOf(val);
                        param = i;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (Boolean.class == paramClazz || boolean.class == paramClazz) {
                    try {
                        Boolean b = Boolean.valueOf(val);
                        param = b;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                if (param != null) {
                    invoke(object, method, param);
                }

                break;
            }

        }
    }

    public static void invoke(Object object, Method method, Object... val) {
        try {
            method.invoke(object, val);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
