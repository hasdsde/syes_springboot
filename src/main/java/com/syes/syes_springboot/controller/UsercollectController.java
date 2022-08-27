package com.syes.syes_springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Usercollect;
import com.syes.syes_springboot.mapper.UsercollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author eula
 * @since 2022-08-26
 */
@RestController
@RequestMapping("/usercollect")
public class UsercollectController {

    @Autowired
    UsercollectMapper usercollectMapper;

    //新建
    @PostMapping("/")
    public Result SaveItem(@RequestBody Usercollect usercollect) {
        usercollectMapper.insert(usercollect);
        return Result.success();
    }

    //删除
    @DeleteMapping("/{userid}/{itemid}")
    public Result deleteItem(@PathVariable("userid") String userid, @PathVariable("itemid") int itemid) {
        usercollectMapper.deleteusercollect(userid, itemid);
        return Result.success();
    }
    
    //查询收藏人的数量
    @GetMapping("/itemid/{itemid}")
    public long queryByItem(@PathVariable("itemid") int itemid) {
        QueryWrapper<Usercollect> wrapper = new QueryWrapper<>();
        wrapper.eq("itemid", itemid);
        Long count = usercollectMapper.selectCount(wrapper);
        return count;
    }

    //根据userid查询收藏物品
    @GetMapping("/userid/{userid}")
    public Result getCommentByUserId(@PathVariable String userid) {
        QueryWrapper<Usercollect> wrapper = new QueryWrapper<>();
        wrapper.eq("userid", userid);
        List<Usercollect> res = usercollectMapper.selectList(wrapper);
        return Result.success(res);
    }


    //分页查询
    @GetMapping("/page")
    public Result slectByPage(
            @RequestParam("pagesize") int pagesize,
            @RequestParam("currentpage") int currentPage,
            @RequestParam("searchtext") String SearchText
    ) {
        Integer total;
        int StartPage = (currentPage - 1) * pagesize; //开始页数
        List<Usercollect> usercollectList = new ArrayList<>();
        QueryWrapper<Usercollect> wrapper = new QueryWrapper<>();
        wrapper.eq("userid", SearchText);
        if (SearchText.isEmpty()) {
            usercollectList = usercollectMapper.slectByPage(StartPage, pagesize); //列表
            total = usercollectMapper.selectCount(null).intValue(); //获取总数
        } else {
            usercollectList = usercollectMapper.slectByPageSearch(StartPage, pagesize, SearchText); //列表
            total = usercollectMapper.selectCount(wrapper).intValue(); //获取总数
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", usercollectList);
        map.put("total", total);
        return Result.success(map);
    }
}
