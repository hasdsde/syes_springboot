package com.syes.syes_springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class JwtInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    JwtAuthenticationInterceptor interceptor;

    @Autowired
    RootJwtAuthenticationInterceptor rootJwtAuthenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login", "/file/img/*", "/rootuser/login", "/file/upload", "/chatServer/*");

//        registry.addInterceptor(rootJwtAuthenticationInterceptor)
//                .addPathPatterns("/actuator/**", "/druid/**");
    }

}
