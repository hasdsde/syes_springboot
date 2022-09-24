package com.syes.syes_springboot.aop;

import com.syes.syes_springboot.mapper.ChatMapper;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ChatCacheHandler {

    @Autowired
    ChatMapper chatMapper;
    @Autowired
    RedisTemplate redisTemplate;

//    @After(value = "execution(*  com.syes.syes_springboot.component.WebSocketServer.onClose(..) )")
//    public void storeCache(JoinPoint joinPoint) {
//
//    }

}
