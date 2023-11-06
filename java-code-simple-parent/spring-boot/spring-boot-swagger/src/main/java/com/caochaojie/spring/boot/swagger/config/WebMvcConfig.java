package com.caochaojie.spring.boot.swagger.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author caochaojie
 * 2023/1/11
 * @description
 */
@Configuration
public class WebMvcConfig implements WebMvcRegistrations {

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new ExtendControllerRequestMappingHandlerConfig();
    }
}
