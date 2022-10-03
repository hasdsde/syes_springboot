package com.syes.syes_springboot.controller;

import com.syes.syes_springboot.Utils.IdUtil;
import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Auction;
import com.syes.syes_springboot.entity.Order;
import com.syes.syes_springboot.mapper.AuctionMapper;
import com.syes.syes_springboot.mapper.ItemMapper;
import com.syes.syes_springboot.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author eula
 * @since 2022-08-26
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    ItemMapper itemMapper;

    @Autowired
    AuctionMapper auctionMapper;


    //客户端新建order
    @GetMapping("/u")
    public Result USave(@RequestParam("itemid") int itemid, @RequestParam("auction") int auction, HttpServletRequest request) {
        //获取出价的用户的id
        Auction auction1 = auctionMapper.selectById(auction);
        String Userid = IdUtil.getId(request);
        Order order = new Order();
        order.setUserid(Userid);
        order.setItemid(itemid);
        order.setTouserid(auction1.getUserid());
        order.setPrice(auction1.getPrice());
        order.setCreatetime(LocalDateTime.now());
        orderMapper.insert(order);
        int i = auctionMapper.setStatus(auction1.getUserid(), itemid);
        return Result.success();
    }


    //根据id修改当前状态
    @GetMapping("/status2")
    public Result updateStatusById2(@RequestParam("id") int id) {
        Order order1 = orderMapper.selectById(id);
        if (order1.getStatus() == 2) {
            order1.setStatus(order1.getStatus() + 1);
        }
        Order order = new Order();
        order.setId(id);
        order.setPrice(order1.getPrice());
        order.setStatus(order1.getStatus());
        orderMapper.updateById(order);
        return Result.success();
    }

    //根据id修改当前状态
    @GetMapping("/status")
    public Result updateStatusById(@RequestParam("id") int id) {
        Order order1 = orderMapper.selectById(id);
        if (order1.getStatus() == 1) {
            order1.setStatus(order1.getStatus() + 1);
        }
        Order order = new Order();
        order.setId(id);
        order.setPrice(order1.getPrice());
        order.setStatus(order1.getStatus());
        orderMapper.updateById(order);
        return Result.success();
    }

}
