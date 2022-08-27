package com.syes.syes_springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.syes.syes_springboot.Utils.JwtUtil;
import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.config.BusinessException;
import com.syes.syes_springboot.entity.Dto.UserDto;
import com.syes.syes_springboot.entity.User;
import com.syes.syes_springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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

    //登录
    @PostMapping("/login")
    public Result Login(
            @RequestBody UserDto userDto,
            HttpServletRequest request) {
        User user = userMapper.selectById(userDto.getUserid());
        if (Objects.equals(user.getPassword(), userDto.getPassword())) {
            String token = JwtUtil.CreateToken(user.getId(), user.getRealname(), user.getPassword());
            return Result.success(token);
        } else {
            throw new BusinessException("401", "密码错误");
        }
    }

    //测试用
    @GetMapping("/{id}")
    public Result TestUser(@PathVariable("id") String id) {
        User user = userMapper.selectById(id);
        return Result.success(user);
    }

    //新建用户
    @PostMapping("/")
    public Result SaveUser(@RequestBody User user) {
        user.setCreatetime(LocalDateTime.now());
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
        return Result.success(id);
    }


    //分页查询
    @GetMapping("/page")
    public Result slectByPage(@RequestParam("pagesize") int pagesize, @RequestParam("currentpage") int currentPage, @RequestParam("searchtext") String SearchText) {

        Integer total;
        int StartPage = (currentPage - 1) * pagesize; //开始页数
        List<User> userList = new ArrayList<>();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", SearchText);
        if (SearchText.isEmpty()) {
            userList = userMapper.slectByPage(StartPage, pagesize); //列表
            total = userMapper.selectCount(null).intValue(); //获取总数
        } else {
            userList = userMapper.slectByPageSearch(StartPage, pagesize, SearchText); //列表
            total = userMapper.selectCount(wrapper).intValue(); //获取总数
        }


        HashMap<String, Object> map = new HashMap<>();
        map.put("data", userList);
        map.put("total", total);
        return Result.success(map);
    }
}
