package com.syes.syes_springboot.schedule;

import com.syes.syes_springboot.mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
        Logger logger = LogManager.getLogger(BeanLifeTest.class);
        logger.debug("debug测试");
        logger.info("info测试");
        logger.warn("warn测试");
    }

    @PreDestroy
    public void PreDestroy() {
        System.out.println("Springboot关闭");
    }
}
