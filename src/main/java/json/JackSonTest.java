package json;

import json.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenlw
 * @since 2020-06-29
 */
public class JackSonTest {

    public static void main(String[] args) {
        testMapToJsonString();
    }

    public static void testMapToJsonString() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("key1", "key1");
        dataMap.put("key2", "key2");
        dataMap.put("key3", "key3");
        dataMap.put("key4", "key4");
        Map<String, Object> approveConfigs = new HashMap<>();
        approveConfigs.put("op", "op");

        dataMap.put("approveConfigs", approveConfigs);
        System.out.println(JsonUtils.toJSONString(dataMap));
    }

}
