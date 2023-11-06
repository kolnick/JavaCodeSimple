package com.caochaojie.bean;

import cn.hutool.core.bean.BeanUtil;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caochaojie
 * 2022/8/5
 * @description
 */
@Slf4j
public class BeanUtilTest {
    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class A {
        private String id;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class B {
        private String id;
        private String name;
    }

    @Test
    public void copyList() {
        List<B> list = new ArrayList<>();
        B build = B.builder().id("1").name("ccj").build();
        list.add(build);

        List<A> as = BeanUtil.copyToList(list, A.class);
        log.info("{}", as);
    }



}
