package com.caochaojie.autoconfig;

import com.caochaojie.auto.config.DataConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author caochaojie
 * 2022/12/12
 * @description
 */

@SpringBootTest(classes = AutoApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class TestAutoConfig {


    @Autowired
    private DataConfig dataConfig;

    @Test
    public void test() {
        log.info("{}", dataConfig);
    }
}
