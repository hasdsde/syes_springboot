package com.syes.syes_springboot.controller;

import com.syes.syes_springboot.Utils.IdUtil;
import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.mapper.UserhistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * <p>
 * 用户浏览历史 前端控制器
 * </p>
 *
 * @author hasdsd
 * @since 2022-09-15
 */
@RestController
@RequestMapping("/userhistory")
public class UserhistoryController {
    @Autowired
    UserhistoryMapper userhistoryMapper;

    //删除全部记录
    @GetMapping("/delAll")
    public Result delAll(HttpServletRequest request) {
        String id = IdUtil.getId(request);
        HashMap<String, Object> map = new HashMap<>();
        map.put("userid", id);
        userhistoryMapper.deleteByMap(map);
        return Result.success("操作成功");
    }

    //删除一个记录
    @GetMapping("/delOne")
    public Result delone(HttpServletRequest request, @RequestParam("itemid") int itemid) {
        String id = IdUtil.getId(request);
        HashMap<String, Object> map = new HashMap<>();
        map.put("itemid", itemid);
        map.put("userid", id);
        userhistoryMapper.deleteByMap(map);
        return Result.success("操作成功");
    }
}
