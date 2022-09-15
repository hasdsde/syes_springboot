package com.syes.syes_springboot.schedule;

import com.syes.syes_springboot.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

@EnableScheduling
@Configuration
public class RedisSchedule {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    ItemMapper itemMapper;

    //每一分钟输出redis储存的item浏览量
    @Scheduled(cron = "0 */10 * * * ?")
    private void AddRedisIntoMysql() {
        Set items = redisTemplate.opsForHash().keys("item");
        for (Object item : items) {
            Object count = redisTemplate.opsForHash().get("item", item.toString());
            System.out.printf(item.toString() + ":" + count.toString() + "\n");
            itemMapper.plusVisited(Integer.parseInt(item.toString()), Integer.parseInt(count.toString()));
        }
        //注意这里是清除全部数据
        redisTemplate.delete("item");
        System.out.println("已将Redis浏览量缓存存入Mysql");
    }
}
