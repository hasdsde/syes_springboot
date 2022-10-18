package com.syes.syes_springboot.controller;

import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Roll;
import com.syes.syes_springboot.mapper.RollMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 抽卡吃饭 前端控制器
 * </p>
 *
 * @author hasdsd
 * @since 2022-10-17
 */
@RestController
@RequestMapping("/roll")
public class RollController {
    @Autowired
    RollMapper rollMapper;

    //增
    @PostMapping("/")
    public Result addRoll(@RequestBody Roll roll) {
        rollMapper.insert(roll);
        return Result.success();
    }

    //删
    @DeleteMapping("/{id}")
    public Result delRoll(@PathVariable("id") int id) {
        rollMapper.deleteById(id);
        return Result.success();
    }

    //改
    @PutMapping("/")
    public Result updateRoll(@RequestBody Roll roll) {
        rollMapper.updateById(roll);
        return Result.success();
    }

    //查全部
    @GetMapping("/")
    public Result selectRoll() {
        List<Roll> rollList = rollMapper.selectAll();
        return Result.success(rollList);
    }

    //查可用
    @GetMapping("/enable")
    public Result selectEnableRoll() {
        List<Roll> rollList = rollMapper.selectEnable();
        return Result.success(rollList);
    }

    //换状态
    @GetMapping("/status")
    public Result setStatus(@RequestParam("id") int id, @RequestParam("status") Boolean status) {
        Roll roll = new Roll();
        roll.setId(id);
        roll.setEnable(status ? 1 : 0);
        rollMapper.updateById(roll);
        return Result.success();
    }
}
