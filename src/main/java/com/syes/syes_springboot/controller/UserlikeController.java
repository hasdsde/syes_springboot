package com.syes.syes_springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.syes.syes_springboot.Utils.IdUtil;
import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Userlike;
import com.syes.syes_springboot.mapper.UserlikeMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户点赞
 * </p>
 *
 * @author hasdsd
 * @since 2022-09-05
 */
@RestController
@RequestMapping("/userlike")
public class UserlikeController {

    @Resource
    UserlikeMapper userlikeMapper;

    //改变情况
    @GetMapping("/change")
    public Result ChangeStatus(@RequestParam("itemid") int itemid, HttpServletRequest request) {
        String userid = IdUtil.getId(request);
        QueryWrapper<Userlike> wrapper = new QueryWrapper<>();
        wrapper.eq("itemid", itemid);
        wrapper.eq("userid", userid);
        Long aLong = userlikeMapper.selectCount(wrapper);
        if (aLong == 1) {
            userlikeMapper.delete(wrapper);
        }
        if (aLong == 0) {
            Userlike userlike = new Userlike();
            userlike.setItemid(itemid);
            userlike.setUserid(userid);
            userlikeMapper.insert(userlike);
        }
        return Result.success();
    }

    //点赞量和是否点赞
    @GetMapping("/queryByItemId")
    public Result queryByItemId(@RequestParam("itemid") int itemid, HttpServletRequest request) {
        String userid = IdUtil.getId(request);
        Userlike userlike = userlikeMapper.quertAll(itemid, userid);
        return Result.success(userlike);
    }
}
