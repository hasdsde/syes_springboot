package com.syes.syes_springboot.aop;

import com.syes.syes_springboot.Utils.IdUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class VisitedCountHandler {

    @After(value = "execution(*  com.syes.syes_springboot.controller.ItemController.itemById(..)) )")
    public void before(JoinPoint joinPoint) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String id = IdUtil.getId(request);
        System.out.println("userid=" + id);
        Object[] args = joinPoint.getArgs();
        Object itemid = args[0].toString();
        Object header = args[0];
        System.out.println(itemid);
        System.out.println("调用了");
    }
}
