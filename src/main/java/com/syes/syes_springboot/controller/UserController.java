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

import java.util.HashMap;
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
            @RequestBody UserDto userDto) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", userDto.getUserid());
        if (userMapper.selectCount(wrapper) == 0) {
            throw new BusinessException("499", "用户不存在");
        }
        User user = userMapper.selectById(userDto.getUserid());
        if (Objects.equals(user.getPassword(), userDto.getPassword())) {
            String token = JwtUtil.CreateToken(user.getId(), user.getRealname(), user.getPassword());
            HashMap<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("username", user.getNickname());
            data.put("avatar", user.getAvatar());
            data.put("userid", user.getId());
            data.put("infoid", user.getInfoid());
            return Result.success(data);
        } else {
            throw new BusinessException("401", "用户名或密码错误");
        }
    }

    @GetMapping("/getinfo/{infoId}")
    public Result GetChatInfo(@PathVariable("infoId") String infoId) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("infoid", infoId));
        User Ruser = new User();
        try {
            Ruser.setInfoid(user.getInfoid());
            Ruser.setNickname(user.getNickname());
            Ruser.setAvatar(user.getAvatar());
        } catch (Exception e) {
            throw new BusinessException("该用户不存在");
        }
        return Result.success(Ruser);
    }
}
