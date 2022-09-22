package com.syes.syes_springboot.controller;

import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Rollimg;
import com.syes.syes_springboot.mapper.RollimgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    //查询所有
    @GetMapping("/")
    public Result queryAllRollimg() {
        List<Rollimg> ro = rollimgMapper.queryallarollimg();
        return Result.success(ro);
    }


}
