package com.syes.syes_springboot;

import com.syes.syes_springboot.entity.Item;
import com.syes.syes_springboot.mapper.ItemMapper;
import com.syes.syes_springboot.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class SyesSpringbootApplicationTests {

    @Resource
    ItemMapper itemMapper;

    @Resource
    UserMapper userMapper;


    @Test
    void contestLoads() {
//        User user = userMapper.selectById(1);
//        System.out.println(user);

        Item item = itemMapper.selectById(-1);
        System.out.println(item);

//        Item item = itemMapper.queryItemById(1);
//        System.out.println(item);
    }

}
