package com.caochaojie.logback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.caochaojie")
@Slf4j
public class LogbackApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogbackApplication.class,args);

        log.trace("Spring boot启动初始化了");
        log.debug("Spring boot启动初始化了");
        log.info("Spring boot启动初始化了");
        log.warn("Spring boot启动初始化了");
        log.error("Spring boot启动初始化了");
    }
}