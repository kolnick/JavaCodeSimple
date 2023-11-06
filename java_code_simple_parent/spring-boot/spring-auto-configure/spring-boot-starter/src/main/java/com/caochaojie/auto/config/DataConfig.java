package com.caochaojie.auto.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author caochaojie
 * 2022/12/12
 * @description
 */

@Component
@ConfigurationProperties(prefix = "dam")
@Data
public class DataConfig {
    private String id;
    private String name;
}
