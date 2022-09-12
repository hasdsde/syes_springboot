package com.syes.syes_springboot.Utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Resource
    RedisTemplate<String, Object> redisTemplate;

    //删除
    public void delete(String key) {
        this.redisTemplate.delete(key);
    }

    //添加
    public Long add(String key, String value) {
        return this.redisTemplate.opsForHyperLogLog().add(key, value);
    }

    //获取总数
    public long size(String key) {
        return this.redisTemplate.opsForHyperLogLog().size(key);
    }

    //是否存在key
    public boolean isExist(String key) {
        return Boolean.TRUE.equals(this.redisTemplate.hasKey(key));
    }

    //设置String类型的值
    public void setStringValue(String key, String value) {
        this.redisTemplate.opsForValue().set(key, value);
    }

    //设置String类型的值并设置时间
    public void setStringValueAndTime(String key, String value, Long time) {
        this.redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    //获取String类型的Value
    public String getStringValue(String key) {
        return (String) this.redisTemplate.opsForValue().get(key);
    }
}
