package com.syes.syes_springboot.config;

import com.syes.syes_springboot.entity.User;
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
        User user = userMapper.selectById("20201313013");
        System.out.println(user);
        System.out.println("Springboot启动");
    }

    @PreDestroy
    public void PreDestroy() {
        System.out.println("Springboot关闭");
    }
}
