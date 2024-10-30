package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @进行特定域名跨域请求的访问允许,这个配置需要重启服务
 *
 */

@Configuration(proxyBeanMethods = false)
public class CorsConfigurer implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  //允许访问的路径
                .allowCredentials(true)
                .allowedOrigins("*")           //允许访问的源,这样配置才能让局域网设备进行访问
//                .allowedOrigins("http://localhost:3000","http://127.0.0.1:3000")           //允许访问的源
                .allowedMethods("*")//允许请求方法
                .maxAge(16800)
                .allowedHeaders("*")           //允许头部设置
                .allowCredentials(true);      //是否发送cookie
        ;               //预检间隔时间
    }
}
