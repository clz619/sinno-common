package win.sinno.common.util;

import java.io.IOException;
import java.io.Serializable;
import org.junit.Test;

/**
 * win.sinno.common.util.SerializeUtilTest
 *
 * @author chenlizhong@qipeng.com
 * @date 2018/9/25
 */
public class SerializeUtilTest {

  @Test
  public void testSerialize1() throws IOException {
    A a = new A();
    a.setA("hhh");
    byte[] bs = SerializeUtil.objectToByte(a);
    for (int i = 0; i < bs.length; i++) {
      System.out.print(bs[i]);
      System.out.print(",");
    }
//    System.out.println(bs);
  }

  @Test
  public void testSerialize2() throws IOException, ClassNotFoundException {
    byte[] bs = {-84, -19, 0, 5, 115, 114, 0, 41, 119, 105, 110, 46, 115, 105, 110, 110, 111, 46,
        99, 111, 109, 109, 111, 110, 46, 117, 116, 105, 108, 46, 83, 101, 114, 105, 97, 108, 105,
        122, 101, 85, 116, 105, 108, 84, 101, 115, 116, 36, 65, 62, 111, -14, -31, -73, 64, 102, 89,
        2, 0, 1, 76, 0, 1, 97, 116, 0, 18, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116,
        114, 105, 110, 103, 59, 120, 112, 116, 0, 3, 104, 104, 104};

    System.out.println(SerializeUtil.byteToObject(bs));
  }


  public static class A implements Serializable {

    private static final long serialVersionUID = 4499081604022429273L;
    private String a;

    private String b;

    public String getA() {
      return a;
    }

    public void setA(String a) {
      this.a = a;
    }

    public String getB() {
      return b;
    }

    public void setB(String b) {
      this.b = b;
    }

    @Override
    public String toString() {
      return "A{" +
          "a='" + a + '\'' +
          ", b='" + b + '\'' +
          '}';
    }
  }
}
