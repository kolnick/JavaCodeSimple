package com.caochaojie.springcloud.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author caochaojie
 * @date 2022/3/12
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/add")
    public String add() {
        String message = restTemplate.getForObject("http://stock-service/stock/reduct", String.class);
        System.out.println("下单成功");
        return "下单成功" + message;
    }

}
