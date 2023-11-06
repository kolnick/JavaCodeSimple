package com.caochaojie.springboot;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author caochaojie
 * 2022/12/9
 * @description
 */
@SpringBootTest(classes = SpringApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class TestSpringBBean {

    @Test
    public void test() {
        System.out.println("bean init");
    }

}
