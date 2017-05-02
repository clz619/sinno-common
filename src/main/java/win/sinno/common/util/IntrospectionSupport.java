package win.sinno.common.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.*;

/**
 * object introspection support
 * <p>
 * populate properties from object's field
 * <p>
 * set object's field from properties
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/4/19 14:22
 */
public final class IntrospectionSupport {

    private static final Logger LOG = LoggerFactory.getLogger(IntrospectionSupport.class);

    public static boolean getProperties(Object target, Map<String, Object> props, String optionPrefix) {

        boolean rc = false;

        if (target == null) {
            throw new IllegalArgumentException("target was null.");
        }
        if (props == null) {
            throw new IllegalArgumentException("props was null.");
        }

        Class<?> clazz = target.getClass();
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {

            if (isGetter(method)) {
                String name = method.getName();

                try {
                    Object value = method.invoke(target);
                    if (value == null) {
                        continue;
                    }

                    String fieldName = getFieldName4Getter(name);
                    if (StringUtils.isNotEmpty(optionPrefix)) {
                        props.put(optionPrefix + fieldName, value);
                    } else {
                        props.put(fieldName, value);
                    }
                    rc = true;

                } catch (Exception ignore) {
                    LOG.error(ignore.getMessage(), ignore);
                }
            }

        }

        return rc;
    }

    public static boolean isGetter(Method method) {

        String name = method.getName();
        Class<?> type = method.getReturnType();
        Class<?> params[] = method.getParameterTypes();

        return (name.startsWith("is") || name.startsWith("get")) && params.length == 0 && type != null;
    }


    /**
     * set props
     *
     * @param target
     * @param props
     * @param optionPrefix
     * @return
     */
    public static boolean setProperties(Object target, Map<String, Object> props, String optionPrefix) {
        boolean rc = false;

        if (target == null) {
            throw new IllegalArgumentException("target is null.");
        }
        if (props == null) {
            throw new IllegalArgumentException("props is null.");
        }
        if (optionPrefix == null) {
            optionPrefix = "";
        }

        for (Iterator<String> iter = props.keySet().iterator(); iter.hasNext(); ) {
            String name = iter.next();

            if (name.startsWith(optionPrefix)) {

                Object val = props.get(name);
                name = name.substring(optionPrefix.length());

                if (setProperty(target, name, val)) {
                    iter.remove();
                    rc = true;
                }
            }

        }

        return rc;
    }

    /**
     * extract props
     *
     * @param props
     * @param optionPrefix
     * @return
     */
    public static Map<String, Object> extractProperties(Map<String, Object> props, String optionPrefix) {

        if (props == null) {
            throw new IllegalArgumentException("props is null.");
        }

        if (optionPrefix == null) {
            throw new IllegalArgumentException("option prefix is null.");
        }

        Map<String, Object> rc = new HashMap<String, Object>(props.size());

        for (Iterator<String> iter = props.keySet().iterator(); iter.hasNext(); ) {
            String name = iter.next();

            if (name.startsWith(optionPrefix)) {
                Object val = props.get(name);
                name = name.substring(optionPrefix.length());
                rc.put(name, val);

                iter.remove();
            }
        }

        return rc;
    }

    public static boolean setProperty(Object target, String name, Object value) {

        try {
            Class<?> clazz = target.getClass();
            Method setter = findSetter(clazz, name);

            if (setter == null) {
                return false;
            }

            // param type
            Class<?> paramType = setter.getParameterTypes()[0];


            if (value == null || value.getClass() == paramType) {
                setter.invoke(target, value);
            } else if (wrapType(value.getClass()) == wrapType(paramType)) {
                setter.invoke(target, value);
            } else {
                // deal primitive
                LOG.info("could not set property {} ,value:{} on {}", new Object[]{name, value, target});
            }

            return false;

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * wrap type
     *
     * @return
     */
    public static Class<?> wrapType(Class<?> type) {

        if (!type.isPrimitive()) {
            return type;
        }

        Class<?> rc = type;
        if (type == int.class) {
            rc = Integer.class;
        } else if (type == long.class) {
            rc = Long.class;
        } else if (type == double.class) {
            rc = Double.class;
        } else if (type == float.class) {
            rc = Float.class;
        } else if (type == short.class) {
            rc = Short.class;
        } else if (type == byte.class) {
            rc = Byte.class;
        } else if (type == boolean.class) {
            rc = Boolean.class;
        }

        return rc;
    }

    /**
     * find field name's setter
     *
     * @param clazz
     * @param filedName
     * @return
     */
    public static Method findSetter(Class clazz, String filedName) {
        String setterName = "set" + Character.toUpperCase(filedName.charAt(0)) + filedName.substring(1);
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            Class<?>[] params = method.getParameterTypes();
            if (method.getName().equals(setterName) && params.length == 1) {
                return method;
            }
        }

        return null;
    }

    public static String getFieldName4Getter(String getter) {

        String name = null;

        if (getter.startsWith("get")) {
            //get*()
            name = getter.substring(3, 4).toLowerCase(Locale.ENGLISH);

            if (getter.length() > 4) {
                name += getter.substring(4);
            }
        } else {
            //is*()
            name = getter.substring(2, 3).toLowerCase(Locale.ENGLISH);
            if (getter.length() > 3) {
                name += getter.substring(3);
            }
        }

        return name;
    }

    public static String convert2String(Object value, Class type) {
        if (value == null) {
            return null;
        }
        if (value instanceof String) {
            return (String) value;
        }

        if (String[].class.isInstance(value)) {
            String[] array = (String[]) value;
            return StringArrayConverter.convertToString(array);
        }

        if (List.class.isInstance(value)) {
            StringBuilder sb = new StringBuilder("[");
            List list = (List) value;
            int size = list.size();
            int sized1 = size - 1;
            for (int i = 0; i < size; i++) {

                Object obj = list.get(i);

                sb.append(obj);

                if (i < sized1) {
                    sb.append(",");
                }
            }
            sb.append("]");
        }

        return value.toString();
    }


}
