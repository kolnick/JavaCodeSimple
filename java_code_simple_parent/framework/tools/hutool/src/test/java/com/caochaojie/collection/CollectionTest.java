package com.caochaojie.collection;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author caochaojie
 * @date 2020/01/05
 */
@Slf4j
public class CollectionTest {

    @Test
    public void getIndex() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        Integer integer = CollectionUtil.get(map.values(), 0);
        System.out.println(integer);
    }

    @Test
    public void filter() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        map.values().stream().filter(releaseBuff -> releaseBuff == 3).collect(Collectors.toList()).forEach(map::remove);
        System.out.println(map);
    }

    @Test
    public void foreach() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        CollectionUtil.forEach(list.iterator(), (value, index) -> {
            if (value != 1) {
                System.out.println("hello ");
            }
        });
    }

    @Test
    public void containsAll() {
        Set<Integer> d1 = Sets.newHashSet(1, 2, 3, 4);
        Set<Integer> d2 = Sets.newHashSet(1, 6);
        boolean b = CollectionUtil.containsAll(d2, d1);
        System.out.println(b);
    }
    @org.testng.annotations.Test
    public void mergeStr() {
        Set<String> set = Sets.newHashSet("1", "2", "3");
        String join = String.join(",", set);
        log.info(join);
    }

    @org.testng.annotations.Test
    public void format() {
        Set<String> set = Sets.newHashSet("1", "2", "3");
        String message = String.format("测试%s", set);
        System.out.println(message);
    }
}
