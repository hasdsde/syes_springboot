package com.syes.syes_springboot;

import com.syes.syes_springboot.Utils.IdUtil;
import com.syes.syes_springboot.entity.Comment;
import com.syes.syes_springboot.entity.Item;
import com.syes.syes_springboot.mapper.CommentMapper;
import com.syes.syes_springboot.mapper.ItemMapper;
import com.syes.syes_springboot.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;


@SpringBootTest
class SyesSpringbootApplicationTests {

    @Resource
    ItemMapper itemMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    DataSource dataSource;

    @Resource
    CommentMapper commentMapper;


    @Test
    void contestLoads() {
        List<Comment> comments = commentMapper.slectByPage(10, 1);
        System.out.println(comments);
    }

}
