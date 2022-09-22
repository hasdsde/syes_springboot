package com.syes.syes_springboot.controller;

import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Sort;
import com.syes.syes_springboot.mapper.SortMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    //获取父级分类
    @GetMapping("/p")
    public Result SelectPsort() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("pid", null);
        List<Sort> sorts = sortMapper.selectByMap(map);
        return Result.success(sorts);
    }

    //获取子集分类
    @GetMapping("/nAll")
    public Result SelectNsortAll() {
        List<Sort> sorts = sortMapper.NodeNotNull();
        return Result.success(sorts);
    }

    //根据父级名字获取id
    @GetMapping("/pn")
    public Result GetSortNameByPName(@RequestParam("name") String name) {
        List<Sort> sort = sortMapper.selectNnameByPname(name);
        return Result.success(sort);
    }


}
