package com.syes.syes_springboot.controller;

import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Sort;
import com.syes.syes_springboot.mapper.SortMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hasdsd
 * @since 2022-09-01
 */
@RestController
@RequestMapping("/sort")
public class SortController {
    @Autowired
    SortMapper sortMapper;

    //获取父级id
    @GetMapping("/p")
    public Result SelectPsort() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("pid", null);
        List<Sort> sorts = sortMapper.selectByMap(map);
        return Result.success(sorts);
    }

    @GetMapping("/n")
    public Result SelectNsort() {
        List<Sort> sorts = sortMapper.selectNsort();
        return Result.success(sorts);
    }

    //根据id查询
    @GetMapping("/{id}")
    public Result SelectByid(@PathVariable("id") int id) {
        Sort sort = sortMapper.selectById(id);
        return Result.success(sort);
    }

    //新增
    @PostMapping("/")
    public Result AddSort(@RequestBody Sort sort) {
        int insert = sortMapper.insert(sort);
        return Result.success();
    }

    //修改
    @PutMapping("/")
    public Result Update(@RequestBody Sort sort) {
        int i = sortMapper.updateById(sort);
        return Result.success();
    }

    //删除
    @DeleteMapping("/{id}")
    public Result DeleteByid(@PathVariable("id") int id) {
        int i = sortMapper.deleteById(id);
        return Result.success();
    }

    //一个分类，分你吗页
//    //分页查询
//    @GetMapping("/page")
//    public Result slectByPage(@RequestParam("pagesize") int pagesize, @RequestParam("currentpage") int currentPage, @RequestParam("searchtext") String SearchText) {
//        Integer total;
//        int StartPage = (currentPage - 1) * pagesize; //开始页数
//        QueryWrapper<Sort> wrapper = new QueryWrapper<>();
//        wrapper.eq("id", SearchText);
//        List<Sort> sortList = new ArrayList<>();
//        if (SearchText.isEmpty()) {
//            sortList = sortMapper.slectByPage(StartPage, pagesize); //列表
//            total = sortMapper.selectCount(null).intValue(); //获取总数
//        } else {
//            sortList = sortMapper.slectByPageSearch(StartPage, pagesize, SearchText); //列表
//            total = sortMapper.selectCount(wrapper).intValue(); //获取总数
//        }
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("data", sortList);
//        map.put("total", total);
//        return Result.success(map);
//    }
}
