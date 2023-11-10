package com.caochaojie.collection;

import org.agrona.collections.Int2ObjectHashMap;
import org.agrona.collections.IntArrayList;
import org.apache.lucene.util.RamUsageEstimator;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: caochaojie
 * @Date: 2023-11-10 20:21
 */
public class CollectionTest {


    @Test
    public void listMemory() {
        List<Integer> list = new IntArrayList();
        int size = 100_0000;
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        long objectSize = RamUsageEstimator.sizeOf(list);
        System.out.println(objectSize / 1024);

        List<Integer> jdkList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            jdkList.add(i);
        }
        long jdkListSize = RamUsageEstimator.sizeOf(jdkList);

        System.out.println(jdkListSize / 1024);
    }

    @Test
    public void mapMemory() {
        // 创建一个 MutableIntObjectMap 对象
        Int2ObjectHashMap<Object> map = new Int2ObjectHashMap<>();
        int size = 100_0000;
        for (int i = 0; i < size; i++) {
            // 向 Map 中添加键值对
            map.put(i, "Value " + i);
        }
        long objectSize = RamUsageEstimator.sizeOf(map);
        System.out.println(objectSize/1024);

        Map<Integer, Object> map2 = new HashMap<>();
        for (int i = 0; i < size; i++) {
            map2.put(i, "Value " + i);
        }
        long jdkMapSize = RamUsageEstimator.sizeOf(map2);
        System.out.println(jdkMapSize / 1024);

    }
}
