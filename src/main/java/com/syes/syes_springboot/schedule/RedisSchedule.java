package com.syes.syes_springboot.schedule;

import com.syes.syes_springboot.mapper.ItemMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PreDestroy;
import java.util.Set;

@EnableScheduling
@Configuration
public class RedisSchedule {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    ItemMapper itemMapper;


    Logger logger = LogManager.getLogger(RedisSchedule.class);


    //每一分钟输出redis储存的item浏览量
    @PreDestroy
    @Scheduled(cron = "0 */20 * * * ?")
    private void AddRedisIntoMysql() {
        Set items = redisTemplate.opsForHash().keys("item");
        for (Object item : items) {
            Object count = redisTemplate.opsForHash().get("item", item.toString());
            System.out.printf(item.toString() + ":" + count.toString() + "\n");
            itemMapper.plusVisited(Integer.parseInt(item.toString()), Integer.parseInt(count.toString()));
        }
        //注意这里是清除全部数据
        redisTemplate.delete("item");
        Logger logger = LogManager.getLogger(Autowired.class);
        logger.info("定时任务：Redis缓存存入Mysql");
    }

    @Scheduled(cron = "0 */2 * * * ?")
    private void KeepItAlive() {
        int i = itemMapper.KeepItAlive();
        logger.info("定时任务：保持连接。。。。。");
    }
}
