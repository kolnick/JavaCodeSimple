package com.caochaojie.secrity.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "caochaojie", ignoreUnknownFields = false)
public class MyProperties {

    /**
     * SWAGGER参数
     */
    private final Swagger swagger = new Swagger();

    /**
     * SWAGGER接口文档参数
     */
    @Data
    public static class Swagger {
        private String title;
        private String description;
        private String version;
        private String termsOfServiceUrl;
        private String contactName;
        private String contactUrl;
        private String contactEmail;
        private String license;
        private String licenseUrl;
    }

}
