import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: caochaojie
 * @Date: 2023-11-13 16:08
 */
public class FastUtilTest {


    public static class Bean {
        private String name;

        public Bean() {
            // Empty constructor required for deserialization
        }

        public Bean(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Bean{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        // 创建一个 Int2ObjectMap 对象
        Int2ObjectMap<String> intObjectMap = new Int2ObjectOpenHashMap<>();
        intObjectMap.put(1, "One");
        intObjectMap.put(2, "Two");
        intObjectMap.put(3, "Three");

        // 将 Int2ObjectMap 转换为 Map<Integer, Object>
        Map<Integer, Object> mapToSerialize = new HashMap<>();
        intObjectMap.forEach((key, value) -> mapToSerialize.put(key, value));

        // 序列化 Map
        String jsonString = JSON.toJSONString(mapToSerialize);
        System.out.println("Serialized JSON: " + jsonString);

        // 反序列化 Map
        Map<Integer, Object> deserializedMap = JSON.parseObject(jsonString, new TypeReference<Map<Integer, Object>>() {
        });
        System.out.println("Deserialized Map: " + deserializedMap);
    }


    @Test
    public void test() {

        // 创建一个 Int2ObjectMap 对象
        Int2ObjectOpenHashMap<String> intObjectMap = new Int2ObjectOpenHashMap<>();
        intObjectMap.put(1, "One");
        intObjectMap.put(2, "Two");
        intObjectMap.put(3, "Three");

        // 序列化 Map
        String jsonString = JSON.toJSONString(intObjectMap);
        System.out.println("Serialized JSON: " + jsonString);

        Int2ObjectOpenHashMap<String> stringInt2ObjectOpenHashMap = deserializeIntObjectMap(jsonString);
        System.out.println(stringInt2ObjectOpenHashMap);
    }

    // 序列化 Int2ObjectOpenHashMap
    private static String serializeIntObjectMap(Int2ObjectOpenHashMap<String> map) {
        JSONObject jsonObject = new JSONObject();
        map.forEach((key, value) -> jsonObject.put(String.valueOf(key), value));
        return jsonObject.toJSONString();
    }


    // 反序列化 Int2ObjectOpenHashMap
    private static Int2ObjectOpenHashMap<String> deserializeIntObjectMap(String jsonString) {
        JSONObject jsonObject = JSON.parseObject(jsonString);
        Int2ObjectOpenHashMap<String> map = new Int2ObjectOpenHashMap<>();
        jsonObject.forEach((key, value) -> map.put(Integer.parseInt(key), (String) value));
        return map;
    }
}

