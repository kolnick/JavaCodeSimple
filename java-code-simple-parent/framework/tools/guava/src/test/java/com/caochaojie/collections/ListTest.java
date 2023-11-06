package com.caochaojie.collections;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

/**
 * @author caochaojie
 * 2022/7/1
 * @description
 */
@Slf4j
public class ListTest {

    @Test
    public void arrayListMap() {
        ArrayListMultimap<Integer, Integer> listMap = ArrayListMultimap.create();
        listMap.put(3, 2);
        listMap.put(3, 1);
        listMap.put(4, 2);
        log.info(listMap.toString());

        List<Integer> integers = listMap.get(4);
        List<Integer> integers1 = listMap.get(3);
        log.info(integers.toString());
        log.info(integers1.toString());
    }


    @Test
    public void immutableList() {
        List<String> list = ImmutableList.of("a", "b", "c");
        //list.add("323");
        ImmutableList<String> strings = ImmutableList.copyOf(list);
        strings.add("ddd");
        System.out.println(strings);
        System.out.println(list);
    }
}
