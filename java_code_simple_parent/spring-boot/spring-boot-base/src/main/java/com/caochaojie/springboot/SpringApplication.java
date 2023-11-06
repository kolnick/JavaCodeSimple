package com.caochaojie.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;

@SpringBootApplication(scanBasePackages = "com.caochaojie")
@RestController
@Slf4j
@EnableScheduling
public class SpringApplication {
    @Autowired
    private ServletContext servletContext;

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        log.info(servletContext.getContextPath());
        return String.format("Hello %s!", name);
    }
}
