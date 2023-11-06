package com.caochaojie.springboot.init;

import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author caochaojie
 * 2022/12/15
 * @description
 */

@Component(value = "A")
@Data
public class ABean implements CommandLineRunner {

    private String name;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("ABean init");
    }
}
