package com.caochaojie.collection;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * @author caochaojie
 * 2022/10/12
 * @description
 */
public class ListTest {

    @Test
    public void iteratorSet() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 5, 6);
        ListIterator<Integer> integerListIterator = list.listIterator();
        while (integerListIterator.hasNext()) {
            Integer next = integerListIterator.next();
            if (next.equals(5)) {
                integerListIterator.set(100);
            }
        }
        System.out.println(list);
    }
}
