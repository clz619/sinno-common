package win.sinno.common.util;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import org.junit.Test;

/**
 * win.sinno.common.util.TreeSetTest
 *
 * @author chenlizhong@qipeng.com
 * @date 2017/11/2
 */
public class TreeSetTest {

  @Test
  public void set() {
    SortedSet<Integer> sortedSet = new TreeSet<>(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o1 - o2;
      }
    });

    sortedSet.add(3);
    sortedSet.add(2);
    sortedSet.add(4);

    System.out.println(sortedSet);
  }

}
