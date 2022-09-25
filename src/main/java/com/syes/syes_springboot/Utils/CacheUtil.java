package com.syes.syes_springboot.Utils;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.syes.syes_springboot.entity.Chat;
import com.syes.syes_springboot.mapper.ChatMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CacheUtil {
    //将Redis缓存装入MySQL里面
    public static void handleChatSet(RedisTemplate redisTemplate, String id, ChatMapper chatMapper) {
        Set set = redisTemplate.opsForSet().members("ChatCache" + id);
        for (Object o : set) {
            JSONObject entries = JSONUtil.parseObj(o.toString());
            Chat chat = JSONUtil.toBean(entries, Chat.class);
            chatMapper.insert(chat);
        }
    }
}
