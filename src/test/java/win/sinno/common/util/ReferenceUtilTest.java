package win.sinno.common.util;

import org.junit.Test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * reference util
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/7/25 17:49
 */
public class ReferenceUtilTest {


    @Test
    public void testSoft() {
        String val = new String("sr");

        SoftReference<String> sr = new SoftReference<String>(val);

        val = null;

        System.gc();

        System.out.println(sr.get());
        // sr
    }

    @Test
    public void testWeak() {
        String val = new String("wr");

        WeakReference<String> wr = new WeakReference<String>(val);

        val = null;

        System.gc();

        System.out.println(wr.get());
        // null
    }

    @Test
    public void testReferenceQueue() {
        String val = new String("string");

        ReferenceQueue<String> queue = new ReferenceQueue<String>();

        WeakReference<String> wr = new WeakReference<String>(val, queue);
        System.out.println(wr);
        System.out.println(wr.hashCode());
        System.out.println("..........");

        System.gc();
        System.out.println("gc");
        System.out.println(wr.get());
        System.out.println(queue.poll());
        System.out.println("..........");

        val = null;
        System.out.println("set null");
        System.out.println(wr.get());
        System.out.println(queue.poll());
        System.out.println("..........");

        System.gc();
        System.out.println("gc");
        System.out.println(wr.get());
        WeakReference<String> wr1 = (WeakReference<String>) queue.poll();

        System.out.println(wr1);
        System.out.println(wr1.hashCode());

    }
}
