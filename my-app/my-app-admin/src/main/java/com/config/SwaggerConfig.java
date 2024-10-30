package com.config;

import com.common.config.BaseSwaggerConfig;
import com.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.controller")
                .title("我的毕业设计系统")
                .description("my-app相关接口文档")
                .contactName("Xiao")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
