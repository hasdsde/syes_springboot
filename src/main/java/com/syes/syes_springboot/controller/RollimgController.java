package com.syes.syes_springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Rollimg;
import com.syes.syes_springboot.entity.Usercollect;
import com.syes.syes_springboot.mapper.RollimgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hasdsd
 * @since 2022-09-02
 */
@RestController
@RequestMapping("/rollimg")
public class RollimgController {
    @Autowired
    RollimgMapper rollimgMapper;

    //查询
    @GetMapping("/id/{id}")
    public Result queryRollimg(@PathVariable int id) {
        List<Rollimg> ro = rollimgMapper.queryrollimg(id);
        return Result.success(ro);
    }
    //新增
    @GetMapping("/url/{url}")
    public Result addRollimg(@PathVariable String url){
        rollimgMapper.addrollimg(url);
        return Result.success();
    }

    //删除
    @DeleteMapping("/{id}/{url}")
    public Result deleteRollimg(@PathVariable("id") int id , @PathVariable("url") String url){
        int count = rollimgMapper.deleterollimg(id,url);
        System.out.println(count);
        if(count >= 1) return Result.success();
        else return Result.error();
    }


}
