package com.learnsphere.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       System.out.println("Working in Web Config");
    	registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**").excludePathPatterns("/adduser","/verify","/","/login","/logout","/register","/aboutme"); // Apply to all endpoints
    }
}

