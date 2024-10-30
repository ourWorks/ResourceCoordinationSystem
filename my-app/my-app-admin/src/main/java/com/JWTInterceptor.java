package com;/*
 *@program:my-app
 *@author: 苏晓龙
 */

import com.common.service.RedisService;
import com.util.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//日志
@Slf4j
@Component
//尝试解决同源策略被拦截的问题
public class JWTInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        System.out.println("进入后端的拦截器");
        //解决跨域请求问题,OPTIONS是预请求，一点都不能错
        if ("GET".equals(request.getMethod()) || "OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        } else {
            //获取authorization字段的信息
            final String authHeader = request.getHeader("Authorization");
            final String token = authHeader;
            boolean signed = false;
            try {
                //非常简化的逻辑判断了,验证符合加密规则
                signed = jwtUtils.isTokenValid((token));
                log.info("接收到的token为:" + token);
                log.info("token验证结果是" + signed);
            } catch (final ExpiredJwtException e) {
                log.info("token已经过期");
                response.sendError(401,"登录已经过期，请重新登录");
                return false;
            }
//            return signed; 为了测试，暂时全部跳过，进行纯后端的SwaggerUI的测试
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

    }
}
