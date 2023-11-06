package com.caochaojie.springcloud.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author caochaojie
 * @date 2022/3/12
 */
@RestController
@RequestMapping("/order")
public class OrderController {


    @RequestMapping("/add")
    public String add() {
        System.out.println("下单成功");
        return "下单成功";
    }

    @RequestMapping("/flow")
    @SentinelResource(value = "flow", blockHandler = "flowBlockHandler")
    public String flow() {
        return "正常访问";
    }
    public String flowBlockHandler(BlockException e) {
        return "被流控了";
    }

}
