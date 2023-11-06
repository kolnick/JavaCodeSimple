package com.caochaojie.spring.boot.swagger.config;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * @author caochaojie
 * 2023/1/11
 * @description
 */
public class ExtendControllerRequestMappingHandlerConfig extends RequestMappingHandlerMapping {


    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo mappingForMethod = super.getMappingForMethod(method, handlerType);
        if (mappingForMethod == null)
            return null;
        Class<?> superclass = handlerType.getSuperclass();
        return appendParentRequestMapping(superclass, mappingForMethod);
    }

    private RequestMappingInfo appendParentRequestMapping(Class<?> handlerType, RequestMappingInfo mappingInfo) {
        if (handlerType == null) {
            return mappingInfo;
        }

        RequestMapping annotation = handlerType.getAnnotation(RequestMapping.class);
        if (annotation != null && annotation.value().length > 0) {
            mappingInfo = RequestMappingInfo.paths(annotation.value()).build().combine(mappingInfo);
        }
        return appendParentRequestMapping(handlerType.getSuperclass(), mappingInfo);
    }
}
