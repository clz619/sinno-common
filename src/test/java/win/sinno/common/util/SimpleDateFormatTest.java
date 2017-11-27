package win.sinno.common.util;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.junit.Test;

/**
 * win.sinno.common.util.SimpleDateFormatTest
 *
 * @author chenlizhong@qipeng.com
 * @date 2017/11/4
 */
public class SimpleDateFormatTest {

  @Test
  public void testSDF() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    System.out.println(dateFormat.format(System.currentTimeMillis()));
  }

  @Test
  public void testCollection() {
    Set<String> set = new HashSet<>();
    set.add("a");
    set.add("c");
    set.add("b");
    set.add("e");

    Iterator<String> it = set.iterator();

    boolean f = true;

    while (it.hasNext()) {
      String str = it.next();
      System.out.println(str);
      if ("b".equals(str)) {
        it.remove();
        System.out.println("remove b");
        set.add("d");
      }

    }

    System.out.println("end");
  }



}
