package com.caochaojie;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @author caochaojie
 * @date 2020/03/03
 */
@SpringBootApplication(exclude = JacksonAutoConfiguration.class, scanBasePackages = {"com.caochaojie"})
@MapperScan(basePackages = "com.caochaojie")
@Slf4j
@Import(SpringUtil.class)
public class MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
}
