package com.syes.syes_springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.User;
import com.syes.syes_springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hasd
 * @since 2022-08-16
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserMapper userMapper;

    //根据id查询
    @GetMapping("/{id}")
    public Result getUserByid(@PathVariable("id") String id) {
        User user = userMapper.selectById(id);
        return Result.success(user);
    }

    //新建用户
    @PostMapping("/")
    public Result SaveUser(@RequestBody User user) {
        int insert = userMapper.insert(user);
        return Result.success();
    }

    //修改用户
    @PutMapping("/")
    public Result updateUser(@RequestBody User user) {
        int i = userMapper.updateById(user);
        return Result.success();
    }

    //删除用户
    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable("id") String id) {
        userMapper.deleteById(id);
        return Result.success();
    }

    //分页查询
    @GetMapping("/page")
    public Result slectByPage(@RequestParam("pagesize") int pagesize, @RequestParam("currentpage") int currentPage) {

        QueryWrapper wrapper = new QueryWrapper();
        Integer total = userMapper.selectCount(wrapper); //获取总数
        int StartPage = (currentPage - 1) * pagesize; //开始页数
        List<User> userList = userMapper.slectByPage(StartPage, pagesize); //列表

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", userList);
        map.put("total", total);
        return Result.success(map);
    }
}
