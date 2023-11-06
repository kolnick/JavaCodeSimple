package com.caochaojie.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class IndexController {

    @GetMapping("/index")
    @ResponseBody
    public String index() {
        return "Hello World ~";
    }

    @RequestMapping("/login")
    public String showLogin() {
        return "login.html";
    }

    @GetMapping("/home")
    @ResponseBody
    public String home() {
        return "欢迎来到主页 ~";
    }

}

