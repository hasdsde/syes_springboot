package com.syes.syes_springboot;

import com.syes.syes_springboot.entity.User;
import com.syes.syes_springboot.mapper.UserMapper;
import com.syes.syes_springboot.service.IUserService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;


@SpringBootTest
class SyesSpringbootApplicationTests {

    @Resource
    IUserService userService;

    @Test
    void contextLoads() {
        List<User> userList = userService.queryAllUser();
        System.out.println(userList);
    }

}
