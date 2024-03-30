package com.chenbiao.config;

import com.chenbiao.interceptor.LoginCheckIntorcepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 表明这是个配置类
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginCheckIntorcepter loginCheckIntorcepter;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckIntorcepter).addPathPatterns("/**");
    }
}
