package com.syes.syes_springboot.aop;

import com.syes.syes_springboot.mapper.UserhistoryMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

//设置浏览量
@Component
@Aspect
public class VisitedCountHandler {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    UserhistoryMapper userhistoryMapper;

    //触发方法时，获取itemid
    @After(value = "execution(*  com.syes.syes_springboot.controller.ItemController.itemById(..)) )")
    public void before(JoinPoint joinPoint) throws Throwable {
//        //获取参数
//        Object[] args = joinPoint.getArgs();
//        Object itemid = args[0].toString();
//        //将获取的参数传递到Redis
//        redisTemplate.opsForHash().increment("item", itemid, 1);
//        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//        HttpServletRequest request = sra.getRequest();
//        String id = IdUtil.getId(request);
//        //添加历史记录到mysql，我服了怎么这么复杂
//        QueryWrapper<Userhistory> wrapper = new QueryWrapper<>();
//        wrapper.eq("itemid", itemid);
//        wrapper.eq("userid", id);
//        Long count = userhistoryMapper.selectCount(wrapper);
//        Userhistory userhistory = new Userhistory();
//        userhistory.setCreatetime(LocalDateTime.now());
//        userhistory.setUserid(id);
//        userhistory.setItemid(Integer.valueOf(itemid.toString()));
//        if (count > 0) {
//            userhistoryMapper.update(userhistory, wrapper);
//        } else {
//            userhistoryMapper.insert(userhistory);
//        }


    }
}
