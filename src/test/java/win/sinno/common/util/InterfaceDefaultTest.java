package win.sinno.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.Test;

/**
 * win.sinno.common.util.InterfaceDefaultTest
 *
 * @author chenlizhong@qipeng.com
 * @date 2017/10/13
 */
public class InterfaceDefaultTest {

  @Test
  public void test2() {
    System.out.println(48412892334720l & 128);

  }

  @Test
  public void test1() {
    List<Integer> intList = new ArrayList<>();
    intList.add(6);
    intList.add(3);
    intList.add(7);
    intList.add(2);
    intList.add(4);
    intList.add(9);
    System.out.println(intList);

    Collections.sort(intList, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return (o1 - o2) > 0 ? 1 : -1;
      }
    });

    System.out.println(intList);
  }

  interface Defa {

    default void hhh() {
      System.out.println("hhhh");
    }
  }

  class DefaImpl1 implements Defa {

  }

  class DefaImpl2 implements Defa {

    @Override
    public void hhh() {
      System.out.println("hahaha");
    }
  }
}
