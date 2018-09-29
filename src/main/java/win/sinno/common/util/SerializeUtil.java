package win.sinno.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * win.sinno.common.util.SerializeUtil
 *
 * @author chenlizhong@qipeng.com
 * @date 2018/9/25
 */
public class SerializeUtil {

  public static Object byteToObject(byte[] bytes) {
    Object obj = null;
    try {
      ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
      ObjectInputStream oi = new ObjectInputStream(bi);

      obj = oi.readObject();
      bi.close();
      oi.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }


  public static byte[] objectToByte(Object obj) {
    byte[] bytes = null;
    try {
      ByteArrayOutputStream bo = new ByteArrayOutputStream();
      ObjectOutputStream oo = new ObjectOutputStream(bo);
      oo.writeObject(obj);

      bytes = bo.toByteArray();

      bo.close();
      oo.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bytes;
  }
}
