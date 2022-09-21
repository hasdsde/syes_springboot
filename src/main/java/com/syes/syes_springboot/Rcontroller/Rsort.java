package com.syes.syes_springboot.Rcontroller;

import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Sort;
import com.syes.syes_springboot.mapper.SortMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/Rsort")
public class Rsort {
    @Autowired
    SortMapper sortMapper;

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

    //获取父级分类
    @GetMapping("/p")
    public Result SelectPsort() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("pid", null);
        List<Sort> sorts = sortMapper.selectByMap(map);
        return Result.success(sorts);
    }

    //根据父级id获取子集信息
    @GetMapping("/n")
    public Result SelectNsort(@RequestParam("checkid") String checkid) {
        List<Sort> sorts = new ArrayList<>();
        if (checkid.isEmpty()) {
            sorts = sortMapper.selectNsort();
        } else {
            HashMap<String, Object> map = new HashMap<>();
            map.put("pid", checkid);
            sorts = sortMapper.selectByMap(map);
        }
        return Result.success(sorts);
    }
    
    //根据父级名字获取id
    @GetMapping("/pn")
    public Result GetSortNameByPName(@RequestParam("name") String name) {
        List<Sort> sort = sortMapper.selectNnameByPname(name);
        return Result.success(sort);
    }


}
