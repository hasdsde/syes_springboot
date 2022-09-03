package com.syes.syes_springboot.controller;

import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Dto.Item_homeDto;
import com.syes.syes_springboot.mapper.ItemHomeMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/itemHome")
public class ItemHomeController {
    @Resource
    ItemHomeMapper itemHomeMapper;

    @GetMapping("/page")
    public Result query(@RequestParam("pagesize") int pagesize, @RequestParam("currentpage") int currentPage) {
        int StartPage = (currentPage - 1) * pagesize;
        List<Item_homeDto> items = itemHomeMapper.query(StartPage, pagesize);
        return Result.success(items);
    }

}

