import com.alibaba.fastjson2.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.*;

/**
 * @Author: caochaojie
 * @Date: 2023-11-13 19:33
 */
public class FastJSON2Test {
    @Test
    public void test(){
        // 创建一个 Int2ObjectOpenHashMap 对象
        Int2ObjectOpenHashMap<FastUtilTest.Bean> map = new Int2ObjectOpenHashMap<>();
        List<Integer> list = Arrays.asList(1, 2, 3, 4);

        List<Map<Integer, FastUtilTest.Bean>> listMap = new ArrayList<>();

        Map<Integer, FastUtilTest.Bean> beanMap = new HashMap<>();
        beanMap.put(1, new FastUtilTest.Bean("Alice1", 25, list,null));
        beanMap.put(2, new FastUtilTest.Bean("Alice2", 25, list, null));
        beanMap.put(3, new FastUtilTest.Bean("Alice3", 25, list,null));
        listMap.add(beanMap);


        map.put(1, new FastUtilTest.Bean("Alice", 25, list,listMap));
        map.put(2, new FastUtilTest.Bean("Bob", 30, list,listMap));
        map.put(3, new FastUtilTest.Bean("Charlie", 28, list,listMap));


        // 使用Gson进行序列化
        String json = serialize(map);
        System.out.println("Serialized JSON: " + json);

        // 使用Gson进行反序列化
        Int2ObjectOpenHashMap<FastUtilTest.Bean> deserializedMap = deserialize(json);
        System.out.println("Deserialized Map: " + deserializedMap);
    }

    private static String serialize(Int2ObjectOpenHashMap<FastUtilTest.Bean> map) {
        String jsonString = JSON.toJSONString(map);
        return jsonString;
    }

    // 反序列化Int2ObjectOpenHashMap
    private static Int2ObjectOpenHashMap<FastUtilTest.Bean> deserialize(String json) {
        return  JSON.parseObject(json, Int2ObjectOpenHashMap.class);
    }
}
