package com.syes.syes_springboot.controller;

import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Dto.Item_homeDto;
import com.syes.syes_springboot.mapper.ItemHomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/itemHome")
public class ItemHomeController {
    @Resource
    ItemHomeMapper itemHomeMapper;

    @GetMapping("/page")
    public Result query(@RequestParam("pagesize") int pagesize, @RequestParam("currentpage") int currentPage){
        List<Item_homeDto> items = itemHomeMapper.query(currentPage,pagesize);
        return Result.success(items);
    }

}

