package win.sinno.common.util;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * introspection support
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017/4/19 15:33
 */
public class IntrospectionSupportTest {

    @Test
    public void testA() {
        Class<?> primitiveType = int.class;
        Class<?> type = Integer.class;

        System.out.println(primitiveType.equals(type));

    }


    @Test
    public void testGetProperties() {
        Gp gp = new Gp();
        gp.setA("asd");
        gp.setB(2);
        gp.setC(4);
        gp.setD(true);
        gp.setE(false);

        Map<String, Object> props = new HashMap<>();
        IntrospectionSupport.getProperties(gp, props, null);

        System.out.println(props);
    }

    @Test
    public void testSetProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("a", "sdfsdf");
        props.put("b", 32);
        props.put("c", 4);
        props.put("d", false);
        props.put("e", true);

        Gp gp = new Gp();

        IntrospectionSupport.setProperties(gp, props, null);
        System.out.println(gp);
    }

    @Test
    public void testExtractProp() {
        Map<String, Object> props = new HashMap<>();
        props.put("class.name", 701);
        props.put("class.num", 50);
        props.put("class.school", "pyhigh");

        Map<String, Object> newMap = IntrospectionSupport.extractProperties(props, "class.");

        System.out.println(newMap);

    }

    /**
     * gp class
     */
    static class Gp {
        private String a;
        private Integer b;

        private int c;

        private boolean d;
        private Boolean e;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public Integer getB() {
            return b;
        }

        public void setB(Integer b) {
            this.b = b;
        }

        public int getC() {
            return c;
        }

        public void setC(int c) {
            this.c = c;
        }

        public boolean isD() {
            return d;
        }

        public void setD(boolean d) {
            this.d = d;
        }

        public Boolean getE() {
            return e;
        }

        public void setE(Boolean e) {
            this.e = e;
        }

        @Override
        public String toString() {
            return "Gp{" +
                    "a='" + a + '\'' +
                    ", b=" + b +
                    ", c=" + c +
                    ", d=" + d +
                    ", e=" + e +
                    '}';
        }
    }

}
