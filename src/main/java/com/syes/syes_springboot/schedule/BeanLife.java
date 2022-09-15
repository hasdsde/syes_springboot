package com.syes.syes_springboot.schedule;

import com.syes.syes_springboot.aop.VisitedCountHandler;
import com.syes.syes_springboot.mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class BeanLife {
    @Autowired
    UserMapper userMapper;
    @Autowired
    VisitedCountHandler visitedCountHandler;

    @PostConstruct
    public void PostConstruct() {
        Logger logger = LogManager.getLogger(BeanLife.class);
        logger.info("Springboot启动");
    }

    @PreDestroy
    public void PreDestroy() {
        Logger logger = LogManager.getLogger(BeanLife.class);
        logger.info("Springboot启动");
    }
}
