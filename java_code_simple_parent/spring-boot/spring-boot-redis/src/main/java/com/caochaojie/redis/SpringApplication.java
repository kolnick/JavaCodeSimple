package com.caochaojie.redis;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
