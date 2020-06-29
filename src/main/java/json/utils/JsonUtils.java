package json.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public final class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    }

    public static <T> T parseObject(final String json, Class<T> clazz) {
        if (null == json || "".equals(json)) {
            return null;
        }
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param content json字符串
     * @return JsonNode
     * <p>
     * zhoul
     */
    public static JsonNode getJsonNode(String content) {
        if (null == content || "".equals(content)) {
            return null;
        }
        return getJsonNode(content.getBytes());
    }

    /**
     * @param content json字符串字节码
     * @return JsonNode
     * <p>
     * zhoul
     */
    public static JsonNode getJsonNode(byte[] content) {
        try {
            return mapper.readTree(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map json2Map(final String json) {
        return parseObject(json, Map.class);
    }

    public static <T> List<T> json2List(final String json, Class<T> cls) {

        if (null == json || "".equals(json)) {
            return null;
        }
        try {
            JavaType t = mapper.getTypeFactory().constructParametricType(
                    List.class, cls);
            return mapper.readValue(json, t);
        } catch (IOException e) {

        }
        return Collections.emptyList();
    }

    public static String toJSONString(final Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {

        }
        return "";
    }
}
