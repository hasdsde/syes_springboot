package com.syes.syes_springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.syes.syes_springboot.Utils.IdUtil;
import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Auction;
import com.syes.syes_springboot.entity.Item;
import com.syes.syes_springboot.entity.User;
import com.syes.syes_springboot.mapper.AuctionMapper;
import com.syes.syes_springboot.mapper.ItemMapper;
import com.syes.syes_springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 前端控制器
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
    @Autowired
    ItemMapper itemMapper;
    @Autowired
    UserMapper userMapper;

    //新增
    @PostMapping("/")
    public Result addAuction(@RequestBody Auction auction, HttpServletRequest request) {
        String id = IdUtil.getId(request);
        auction.setUserid(id);
        auction.setTime(LocalDateTime.now());
        auctionMapper.insert(auction);
        return Result.success();
    }

    //删除
    @DeleteMapping("/{id}")
    public Result deleteAuction(@PathVariable int id) {
        auctionMapper.deleteById(id);
        return Result.success();
    }

    //修改
    @PutMapping("/")
    public Result modifyAuction(@RequestBody Auction auction, HttpServletRequest request) {
        String id = IdUtil.getId(request);
        auction.setUserid(id);
        auction.setTime(LocalDateTime.now());
        auctionMapper.updateById(auction);
        return Result.success();
    }

    //根据物品查用户
    @GetMapping("/queryItem/{itemid}")
    public Result queryItemAuction(@PathVariable int itemid) {
        QueryWrapper<Auction> wrapper = new QueryWrapper<>();
        wrapper.eq("itemid", itemid);
        List<Auction> list = auctionMapper.selectList(wrapper);
        //查询用户头像
        for (Auction auction : list) {
            User user = userMapper.selectById(auction.getUserid());
            auction.setAvatar(user.getAvatar());
            auction.setUsername(user.getNickname());
            auction.setUserid("null");
        }
        return Result.success(list);
    }

    //根据用户查物品
    @GetMapping("/queryUser/{Userid}")
    public Result queryUserAuction(@PathVariable String Userid) {
        QueryWrapper<Auction> wrapper = new QueryWrapper<>();
        wrapper.eq("Userid", Userid);
        List<Auction> list = auctionMapper.selectList(wrapper);
        //查询物品标题
        for (Auction auction : list) {
            Item item = itemMapper.selectById(auction.getItemid());
            auction.setTitle(item.getTitle());
        }
        return Result.success(list);
    }
}
