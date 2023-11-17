package com.caochaojie.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: caochaojie
 * @Date: 2023-11-14 20:08
 */
public class MapTest {

    @Test
    public void iteratorRemove() {
        Map<String, Integer> myMap = new HashMap<>();
        myMap.put("A", 1);
        myMap.put("B", 2);
        myMap.put("C", 3);

        Iterator<Map.Entry<String, Integer>> iterator = myMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            String key = entry.getKey();
            Integer value = entry.getValue();

            // 在迭代过程中删除特定的键值对
            if (key.equals("B")) {
                iterator.remove();
            }

            System.out.println("Key: " + key + ", Value: " + value);
        }

        System.out.println("Map after iteration and removal: " + myMap);
    }

    @Test
    public void forRemove() {
        Map<String, Integer> myMap = new HashMap<>();
        myMap.put("A", 1);
        myMap.put("B", 2);
        myMap.put("C", 3);

        for (Map.Entry<String, Integer> entry : myMap.entrySet()) {
            String key = entry.getKey();
            // 在迭代过程中删除特定的键值对
            if (key.equals("B")) {
                myMap.remove(key);
            }
        }
        System.out.println(myMap);
    }
}

