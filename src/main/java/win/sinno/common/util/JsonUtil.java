package win.sinno.common.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * json util
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2016-04-08 14:23
 */
public final class JsonUtil {
    private static final Logger LOG = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final ObjectMapper OBJECT_MAPPER_NO_QUOTE = new ObjectMapper();

    static {
        OBJECT_MAPPER_NO_QUOTE.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);
        OBJECT_MAPPER_NO_QUOTE.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    private JsonUtil() {
    }

    /**
     * 将POJO转为JSON
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJson(T obj) {
        String json;
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);

        } catch (Exception e) {
            LOG.error("convert POJO to JSON failure", e);
            throw new RuntimeException(e);
        }
        return json;
    }

    public static <T> String toJsonNoQuote(T obj) {
        String json;
        try {
            json = OBJECT_MAPPER_NO_QUOTE.writeValueAsString(obj);

        } catch (Exception e) {
            LOG.error("convert POJO to JSON failure", e);
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * 将JSON转为POJO
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> type) {
        T pojo;
        try {
            pojo = OBJECT_MAPPER.readValue(json, type);
        } catch (Exception e) {
            LOG.error("convert JSON to POJO failure", e);
            throw new RuntimeException(e);
        }
        return pojo;
    }

    /**
     * json字符串转Map
     * 2015年4月3日上午10:41:25
     * auther:shijing
     *
     * @param jsonStr
     * @return
     * @throws IOException
     */
    public static Map<String, Object> parseMap(String jsonStr) throws IOException {
        Map<String, Object> map = OBJECT_MAPPER.readValue(jsonStr, Map.class);
        return map;
    }

}

