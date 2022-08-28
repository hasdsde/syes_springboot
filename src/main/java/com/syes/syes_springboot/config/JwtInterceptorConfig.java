package com.syes.syes_springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class JwtInterceptorConfig implements WebMvcConfigurer {
//    @Autowired
//    JwtAuthenticationInterceptor interceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns("/user/login");
//    }
}
