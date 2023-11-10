package com.caochaojie.collection;

import gnu.trove.list.array.TIntArrayList;
import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import org.agrona.collections.Int2ObjectHashMap;
import org.agrona.collections.IntArrayList;
import org.apache.lucene.util.RamUsageEstimator;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试前需要增加 jvm 参数 不然会报错
 * -ea --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.util=ALL-UNNAMED
 *
 * @Author: caochaojie
 * @Date: 2023-11-10 20:21
 */
public class CollectionTest {


    @Test
    public void listMemory() {
        List<Integer> list = new IntArrayList();
        List<Integer> jdkList = new ArrayList<>();
        TIntArrayList tIntArrayList = new TIntArrayList();
        int size = 100_0000;
        for (int i = 0; i < size; i++) {
            list.add(i);
            jdkList.add(i);
            tIntArrayList.add(i);
        }
        long objectSize = RamUsageEstimator.sizeOf(list);
        System.out.println(objectSize / 1024);
        long jdkListSize = RamUsageEstimator.sizeOf(jdkList);
        System.out.println(jdkListSize / 1024);
        long tIntArrayListSize = RamUsageEstimator.sizeOf(tIntArrayList);
        System.out.println(tIntArrayListSize / 1024);
    }

    @Test
    public void mapMemory() {
        // 创建一个 MutableIntObjectMap 对象
        Int2ObjectHashMap<Object> map = new Int2ObjectHashMap<>();
        Map<Integer, Object> map2 = new HashMap<>();
        TIntObjectMap tIntIntHashMap = new TIntObjectHashMap();
        int size = 100_0000;
        for (int key = 0; key < size; key++) {
            // 向 Map 中添加键值对
            String value = "Value " + key;
            map.put(key, value);
            map2.put(key, value);
            tIntIntHashMap.put(key,value);
        }
        long objectSize = RamUsageEstimator.sizeOf(map);
        System.out.println(objectSize / 1024);
        long jdkMapSize = RamUsageEstimator.sizeOf(map2);
        System.out.println(jdkMapSize / 1024);
        long tIntIntHashMapSize = RamUsageEstimator.sizeOf(tIntIntHashMap);
        System.out.println(tIntIntHashMapSize / 1024);
    }
}
