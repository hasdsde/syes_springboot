package com.syes.syes_springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Item;
import com.syes.syes_springboot.entity.Usercollect;
import com.syes.syes_springboot.mapper.UsercollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
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
    @DeleteMapping("/{id}")
    public Result deleteItem(@PathVariable("id") String id) {
        usercollectMapper.deleteById(id);
        return Result.success();
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
