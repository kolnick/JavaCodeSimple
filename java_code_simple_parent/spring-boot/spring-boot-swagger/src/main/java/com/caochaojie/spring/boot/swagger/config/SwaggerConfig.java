package com.caochaojie.spring.boot.swagger.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("com.caochaojie.spring.boot.swagger.controller"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(securityContexts())
                // 通过Swagger2的securitySchemes配置全局参数
                .securitySchemes(securitySchemes());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档标题")
                .description("文档内容描述")
                .termsOfServiceUrl("http://www.baidu.com")
                .version("1.0")
                .build();
    }
    // 在Swagger2的securityContexts中通过正则表达式，设置需要或不需要使用参数的接口

    private List<ApiKey> securitySchemes() {
        // 设置请求头信息
        List<ApiKey> result = new ArrayList<>();
        // 参数一name为参数名，参数二keyname为参数key，参数三passAs为参数存放位置，其值有header、query、path等，其中
        // header为请求头，query为请求参数，path为请求路径
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", "Header");
        result.add(apiKey);
        return result;
    }

    // 在Swagger2的securityContexts中通过正则表达式，设置需要或不需要使用参数的接口
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> result = new ArrayList<>();
        // 调用下面定义的getContextByPath()，设置需要登录认证的路径，比如此处的/hello/.*
        result.add(getContextByPath("/hello/.*"));
        // 上面配置后，在Swagger文档中访问/hello路径时，需要用到securitySchemes()方法中配置的全局参数
        return result;
    }

    private SecurityContext getContextByPath(String pathRegex) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result = new ArrayList<>();
        // AuthorizationScope权限范围对象，其参数一scope为权限范围名称，global表示全局，其参数二为范围范围描述
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        result.add(new SecurityReference("Authorization", authorizationScopes));
        return result;
    }
}