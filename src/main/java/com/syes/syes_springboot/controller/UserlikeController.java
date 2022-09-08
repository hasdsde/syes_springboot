package com.syes.syes_springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.syes.syes_springboot.Utils.IdUtil;
import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Userlike;
import com.syes.syes_springboot.mapper.UserlikeMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

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

    // 新增
    @PutMapping("")
    public Result insert(@RequestBody Userlike userlike,
                         HttpServletRequest request) {
        userlike.setUserid(IdUtil.getId(request));
        userlikeMapper.insert(userlike);
        return Result.success();
    }

    // 删除
    @DeleteMapping("")
    public Result delete(@RequestBody Userlike userlike,
                         HttpServletRequest request) {

        userlike.setUserid(IdUtil.getId(request));

        QueryWrapper<Userlike> wrapper = new QueryWrapper<>();
        wrapper.eq("userid", userlike.getUserid());
        wrapper.eq("itemid", userlike.getItemid());
        int i = userlikeMapper.delete(wrapper);
        return Result.success(i);
    }

    //点赞量和是否点赞
    @GetMapping("/queryByItemId")
    public Result queryByItemId(@RequestParam("itemid") int itemid, HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        //检测点赞量
        QueryWrapper<Userlike> wrapper = new QueryWrapper<>();
        wrapper.eq("itemid", itemid);
        Long count = userlikeMapper.selectCount(wrapper);
        map.put("counts", count);
        //判断用户是否点赞
        String userid = IdUtil.getId(request);
        wrapper.eq("userid", userid);
        Long Ucount = userlikeMapper.selectCount(wrapper);
        if (Ucount == 1) {
            map.put("status", "true");
        } else {
            map.put("status", "false");
        }
        return Result.success(map);
    }
}
