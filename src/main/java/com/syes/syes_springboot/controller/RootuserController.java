package com.syes.syes_springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.syes.syes_springboot.Utils.JwtUtil;
import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.config.BusinessException;
import com.syes.syes_springboot.entity.Rootuser;
import com.syes.syes_springboot.mapper.RootuserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author eula
 * @since 2022-08-29
 */
@RestController
@RequestMapping("/rootuser")
public class RootuserController {
    @Autowired
    RootuserMapper rootuserMapper;

    //登录
    @PostMapping("/login")
    public Result Login(
            @RequestBody Rootuser rootuser) {
        QueryWrapper<Rootuser> wrapper = new QueryWrapper<>();
        wrapper.eq("id", rootuser.getId());
        if (rootuserMapper.selectCount(wrapper) == 0) {
            throw new BusinessException("499", "root用户不存在");
        }
        Rootuser user = rootuserMapper.selectById(rootuser.getId());
        if (Objects.equals(user.getPassword(), rootuser.getPassword())) {
            String token = JwtUtil.CreateRootToken(user.getId(), user.getUsername(), user.getPassword());
            HashMap<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("username", user.getUsername());
            return Result.success(data);
        } else {
            throw new BusinessException("401", "密码错误");
        }
    }
}
