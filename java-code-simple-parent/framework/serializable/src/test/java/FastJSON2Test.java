import bean.Bean;
import bean.BigObject;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.annotation.JSONType;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Data;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author: caochaojie
 * @Date: 2023-11-13 19:33
 */
public class FastJSON2Test {


    /**
     * 序列化失败直接OOM
     */
    @Test
    public void BigObjectSizeSerialize() {

        List<Integer> list = IntStream.range(0, 100000).boxed().collect(Collectors.toList());

        List<Map<Integer, Bean>> listMap = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Map<Integer, Bean> map1 = new HashMap<>();
            for (int j = 0; j < 100000; j++) {
                map1.put(j, new Bean("Alice", 25, list, null));
            }
            listMap.add(map1);
        }
        BigObject bigObject = new BigObject("Alice", 25, list, listMap);
        String jsonString = JSON.toJSONString(bigObject);
        System.out.println(jsonString);

        BigObject bigObject1 = JSON.parseObject(jsonString, BigObject.class);
        System.out.println(bigObject1);
    }

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


    @Data
    class TestClass {
        private Sex sex;
        private Date date;
        private LocalDateTime localDateTime;
    }

    @Data
    class ArrayClass {
        private int[] array;
    }

    public static class B {
        public A[] a;
    }

    @JSONType(serializeFeatures = {JSONWriter.Feature.BeanToArray}
            , deserializeFeatures = {JSONReader.Feature.SupportArrayToBean}
            , orders = {"id", "name", "ver", "carr"}
    )
    public static class A {
        public int id;
        public String name;
        public int ver;
        public C[] carr;
    }

    public static class C {
        public int id;
        public int count;
    }

    enum Sex {
        man,
        woman,
        ;
    }


    /**
     * 测试不通过  bug
     */
    @Test
    public void testClassABC() {
        A a = new A();
        a.id = 1;
        a.name = "n1";
        a.carr = new C[2];
        a.carr[0] = new C();
        a.carr[0].id = 11;
        a.carr[1] = new C();
        a.carr[1].id = 22;

        A a2 = new A();
        a2.id = 2;
        a2.name = "n2";


        B b = new B();
        b.a = new A[]{a, a2};


        String str = JSON.toJSONString(b);

        B b2 = JSON.parseObject(str, B.class);
        System.out.println(b2);
    }

    @Test
    public void testArrayClassSerialize() {
        ArrayClass arrayClass = new ArrayClass();
        arrayClass.array = new int[]{1, 2, 3, 4};
        String jsonString = JSON.toJSONString(arrayClass);
        System.out.println(jsonString);
        ArrayClass arrayClass1 = JSON.parseObject(jsonString, ArrayClass.class);
        System.out.println(arrayClass1);
    }

    @Test
    public void testEnumClassSerialize() {
        TestClass testClass = new TestClass();
        testClass.sex = Sex.man;
        testClass.date = new Date();
        testClass.localDateTime = LocalDateTime.now();
        String jsonString = JSON.toJSONString(testClass);
        System.out.println(jsonString);
        TestClass testClass1 = JSON.parseObject(jsonString, TestClass.class);
        System.out.println(testClass1);
    }

    @Test
    public void testLocalDateTimeFormat() {
        LocalDateTime localDateTime = LocalDateTime.now();
        JSON.register(LocalDateTime.class, (jsonWriter, object, fieldName, fieldType, features) -> {
            if (object == null) {
                jsonWriter.writeNull();
                return;
            }
            LocalDateTime localTime = (LocalDateTime) object;
            jsonWriter.writeString(localTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        });
        String jsonString = JSON.toJSONString(localDateTime);
        System.out.println(jsonString);
    }


    private static String serialize(Int2ObjectOpenHashMap<Bean> map) {
        String jsonString = JSON.toJSONString(map);
        return jsonString;
    }

    // 反序列化Int2ObjectOpenHashMap
    private static Int2ObjectOpenHashMap<Bean> deserialize(String json) {
        return JSON.parseObject(json, Int2ObjectOpenHashMap.class);
    }
}
