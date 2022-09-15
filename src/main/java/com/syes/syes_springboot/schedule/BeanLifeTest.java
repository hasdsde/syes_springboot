package com.syes.syes_springboot.schedule;

import com.syes.syes_springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class BeanLifeTest {
    @Autowired
    UserMapper userMapper;

    @PostConstruct
    public void PostConstruct() {
        System.out.println("Springboot启动");
    }

    @PreDestroy
    public void PreDestroy() {
        System.out.println("Springboot关闭");
    }
}
