package win.sinno.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * win.sinno.common.util.SerializeUtil
 *
 * @author chenlizhong@qipeng.com
 * @date 2018/9/25
 */
public class SerializeUtil {

  public static Object byteToObject(byte[] bytes) throws IOException, ClassNotFoundException {

    if (bytes == null) {
      throw new NullPointerException("bytes can't be null!");
    }

    Object obj = null;

    try {
      ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
      ObjectInputStream oi = new ObjectInputStream(bi);

      obj = oi.readObject();
      bi.close();
      oi.close();
    } catch (Exception e) {
      throw e;
    }

    return obj;
  }


  public static byte[] objectToByte(Object obj) throws IOException {

    if (obj == null) {
      throw new NullPointerException("obj can't be null!");
    }

    byte[] bytes = null;

    try {
      ByteArrayOutputStream bo = new ByteArrayOutputStream();
      ObjectOutputStream oo = new ObjectOutputStream(bo);
      oo.writeObject(obj);

      bytes = bo.toByteArray();

      bo.close();
      oo.close();
    } catch (Exception e) {
      throw e;
    }

    return bytes;
  }
}
