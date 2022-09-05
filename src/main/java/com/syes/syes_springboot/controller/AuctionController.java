package com.syes.syes_springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Auction;
import com.syes.syes_springboot.mapper.AuctionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hasdsd
 * @since 2022-09-05
 */
@RestController
@RequestMapping("/auction")
public class AuctionController {
    @Autowired
    AuctionMapper auctionMapper;

    //新增
    @GetMapping("/add/{itemid}/{userid}/{price}")
    public Result addAuction(@PathVariable int itemid, @PathVariable String userid ,@PathVariable int price){
        auctionMapper.insert(itemid,userid,price);
        return Result.success();
    }

    //删除
    @DeleteMapping("/{id}")
    public Result deleteAuction(@PathVariable int id){
        auctionMapper.deleteById(id);
        return Result.success();
    }

    //更新
    @GetMapping("/modify/{id}/{price}")
    public Result modifyAuction(@PathVariable int id,@PathVariable int price){
        auctionMapper.updateById(price,id);
        return Result.success();
    }

    //查询
    @GetMapping("/query/{id}")
    public Result queryAuction(@PathVariable int id){
        Auction auction = auctionMapper.selectById(id);
        return Result.success(auction);
    }




}
