package com.syes.syes_springboot.Rcontroller;

import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Rollimg;
import com.syes.syes_springboot.mapper.RollimgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Rrollimg")
public class RRollingController {
    @Autowired
    RollimgMapper rollimgMapper;

    //查询所有
    @GetMapping("/")
    public Result queryAllRollimg() {
        List<Rollimg> ro = rollimgMapper.queryallarollimg();
        return Result.success(ro);
    }

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
