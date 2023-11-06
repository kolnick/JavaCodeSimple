package com.caochaojie.clone;


import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CloneTest {

    @Test
    public void cloneTest() {

        Dog dog = new Dog();
        dog.setAge(1);

        Dog clone = dog.clone();
        clone.setAge(2);
        System.out.println(clone);
        System.out.println(dog);

        System.out.println(0 < 0);
    }

    @Data
    class A {
        private boolean a;
    }

    @Data
    class B {
        private int a;
    }

    @Test
    public void copyProperties() {
        List<A> list = new ArrayList<>();
        List<B> bs = BeanUtil.copyToList(list, B.class);

        System.out.println(bs);
    }
}
