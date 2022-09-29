package com.syes.syes_springboot.aop;

import com.syes.syes_springboot.entity.Dto.ChatDto;
import com.syes.syes_springboot.mapper.ChatMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UnReadMessageHandler {
    @Autowired
    ChatMapper chatMapper;

    @After(value = "execution(* com.syes.syes_springboot.controller.ChatController.getChatHistory(..))")
    public void after(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        ChatDto chatDto = new ChatDto();
        chatMapper.changeReadStatus(chatDto.getUserid(), chatDto.getTouserid());
    }
}
