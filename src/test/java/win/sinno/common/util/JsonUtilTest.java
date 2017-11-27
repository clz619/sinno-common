package win.sinno.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JsonUtilTest {

    @Test
    public void testMap() {
        Map<String, String> map = new HashMap<>();
        map.put("限量", "限糧");
        map.put("低至", "彽至、低臸");
        System.out.println(JsonUtil.toJson(map));

    }

    @Test
    public void testFastjson() {

        Map<String, String>[] rets = new HashMap[2];
        rets[0] = new HashMap<>();

        Map<String, String> map = new HashMap<>();
        map.put("限量", "限糧");
        map.put("低至", "彽至、低臸");
        rets[1] = map;

        String a = JSONArray.toJSONString(rets);
        System.out.println(a);

    }

    class Temp {

        String text;
        Map<String, String> check;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Map<String, String> getCheck() {
            return check;
        }

        public void setCheck(Map<String, String> check) {
            this.check = check;
        }
    }

}
