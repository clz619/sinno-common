package win.sinno.common.util;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/**
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/6/13 11:27
 */
public class UnsafeUtil {

  public static Unsafe getUnsafe() throws IllegalAccessException, NoSuchFieldException {
    Field f = Unsafe.class.getDeclaredField("theUnsafe");
    f.setAccessible(true);

    return (Unsafe) f.get(null);
  }

}
