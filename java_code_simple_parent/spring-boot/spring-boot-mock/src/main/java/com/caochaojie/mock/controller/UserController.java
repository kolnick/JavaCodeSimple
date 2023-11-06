package com.caochaojie.mock.controller;


import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @PostMapping("/post")
    public String post() {
        return "Hello World";
    }

    @PostMapping("/postParams")
    public String post(@Param("name") String name, @Param("year") Integer year) {
        return "name:" + name + " " + "year:" + year;
    }
}
