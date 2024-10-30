package com.config;

/*
 *@program:my-app
 *@author: 苏晓龙
 */

import com.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    JWTInterceptor jwtInterceptor;
    //spring拦截器加载在springcontentText之前，所以这里用@Bean提前加载。否则会导致过滤器中的@AutoWired注入为空

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        addPathPatterns("/**")是拦截所有请求，excludePathPatterns()里面写排除的请求，因为注册登录不需要拦截
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**").excludePathPatterns("/login","/register");
    }

}
