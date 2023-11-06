package com.caochaojie.springboot.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author caochaojie
 * 2022/12/15
 * @description
 */
@Component
@DependsOn("A")
public class BBean {

    @Autowired
    private ABean a;

    @PostConstruct
    public void run() throws Exception {
        System.out.println("B-BeanInit");
    }
}
