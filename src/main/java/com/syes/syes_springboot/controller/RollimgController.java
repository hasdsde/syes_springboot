package com.syes.syes_springboot.controller;

import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Rollimg;
import com.syes.syes_springboot.mapper.RollimgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
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

    //查询所有
    @GetMapping("/")
    public Result queryAllRollimg() {
        List<Rollimg> ro = rollimgMapper.queryallarollimg();
        return Result.success(ro);
    }

    //    //新增
//    @PostMapping("/url")
//    public Result addRollimg(@RequestBody String url) {
//        System.out.println(url);
////        rollimgMapper.addrollimg((String) url);
//        return Result.success(url);
//    }
    //新增
    @GetMapping("/url")
    public Result addRollimg(@RequestParam("url") String url) {
        rollimgMapper.addrollimg(url);
        return Result.success();
    }


    //删除
    @DeleteMapping("/{id}")
    public Result deleteRollimg(@PathVariable("id") int id) {
        rollimgMapper.deleteById(id);
        return Result.success();
    }


}
