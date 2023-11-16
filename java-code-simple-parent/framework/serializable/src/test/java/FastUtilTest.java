import bean.Bean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.*;

/**
 * @Author: caochaojie
 * @Date: 2023-11-13 16:08
 */
public class FastUtilTest {


    @Test
    public void test() {

        // 创建一个 Int2ObjectOpenHashMap 对象
        Int2ObjectOpenHashMap<Bean> map = new Int2ObjectOpenHashMap<>();
        List<Integer> list = Arrays.asList(1, 2, 3, 4);

        List<Map<Integer, Bean>> listMap = new ArrayList<>();

        Map<Integer, Bean> beanMap = new HashMap<>();
        beanMap.put(1, new Bean("Alice1", 25, list, null));
        beanMap.put(2, new Bean("Alice2", 25, list, null));
        beanMap.put(3, new Bean("Alice3", 25, list, null));
        listMap.add(beanMap);


        map.put(1, new Bean("Alice", 25, list, listMap));
        map.put(2, new Bean("Bob", 30, list, listMap));
        map.put(3, new Bean("Charlie", 28, list, listMap));


        // 使用Gson进行序列化
        String json = serialize(map);
        System.out.println("Serialized JSON: " + json);

        // 使用Gson进行反序列化
        Int2ObjectOpenHashMap<Bean> deserializedMap = deserialize(json);
        System.out.println("Deserialized Map: " + deserializedMap);

    }

    // 序列化Int2ObjectOpenHashMap
    private static String serialize(Int2ObjectOpenHashMap<Bean> map) {
        Gson gson = new Gson();
        // 使用TypeToken获取泛型类型信息
        Type type = new TypeToken<Int2ObjectOpenHashMap<Bean>>() {
        }.getType();
        // 执行序列化
        return gson.toJson(map, type);
    }

    // 反序列化Int2ObjectOpenHashMap
    private static Int2ObjectOpenHashMap<Bean> deserialize(String json) {
        Gson gson = new Gson();
        // 使用TypeToken获取泛型类型信息
        Type type = new TypeToken<Int2ObjectOpenHashMap<Bean>>() {
        }.getType();
        // 执行反序列化
        return gson.fromJson(json, type);
    }

}

