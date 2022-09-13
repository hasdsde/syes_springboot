package com.syes.syes_springboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.stereotype.Component;

//设置浏览量
@Component
@Aspect
public class VisitedCountHandler {
    @Autowired
    RedisTemplate redisTemplate;

    //触发方法时，获取itemid
    @After(value = "execution(*  com.syes.syes_springboot.controller.ItemController.itemById(..)) )")
    public void before(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Object itemid = args[0].toString();
        ObjectHashMapper mapper = new ObjectHashMapper();
        redisTemplate.opsForHash().increment("item", itemid, 1);
    }
}
