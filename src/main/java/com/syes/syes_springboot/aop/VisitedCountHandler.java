package com.syes.syes_springboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class VisitedCountHandler {

    @After(value = "execution(*  com.syes.syes_springboot.controller.ItemController.itemById(..)) )")
    public void before(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Object itemid = args[0].toString();
        Object header = args[0];
        System.out.println(itemid);
        System.out.println("调用了");
    }
}
