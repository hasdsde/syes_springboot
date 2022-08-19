package com.syes.syes_springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.mapper.TablemenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author test
 * @since 2022-08-19
 */
@RestController
@RequestMapping("/tablemenu")
public class TablemenuController {

    @Autowired
    TablemenuMapper tablemenuMapper;

    @GetMapping("/{tablename}")
    public Result getMenuByTable(@PathVariable("tablename") String tablename) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tablename", tablename);
        List list = tablemenuMapper.selectList(queryWrapper);
        return Result.success(list);
    }
}
