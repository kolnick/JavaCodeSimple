package com.caochaojie.springcloud.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author caochaojie
 * @date 2022/3/15
 */
@Configuration
public class RibbonRandomRuleConfig {

    @Bean
    public IRule iRule() {
        return new RandomRule();
    }

}
