package com.syes.syes_springboot.controller;

import com.syes.syes_springboot.Utils.IdUtil;
import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Auction;
import com.syes.syes_springboot.entity.Order;
import com.syes.syes_springboot.mapper.AuctionMapper;
import com.syes.syes_springboot.mapper.ItemMapper;
import com.syes.syes_springboot.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    //新建order
    @PostMapping("/")
    public Result SaveOrder(@RequestBody Order order) {
        order.setCreatetime(LocalDateTime.now());
        int insert = orderMapper.insert(order);
        return Result.success();
    }

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

    //修改order
    @PutMapping("/")
    public Result updateOrder(@RequestBody Order order) {
        int i = orderMapper.updateById(order);
        return Result.success();
    }

    //删除order
    @DeleteMapping("/{id}")
    public Result deleteOrder(@PathVariable("id") String id) {
        orderMapper.deleteById(id);
        return Result.success();
    }

    //根据id修改状态
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

    //根据id修改状态
    @GetMapping("/status2")
    public Result updateStatusById2(@RequestParam("id") int id) {
        Order order1 = orderMapper.selectById(id);
        if (order1.getStatus() == 2) {
            order1.setStatus(order1.getStatus() + 1);
        }
        Order order = new Order();
        order.setId(id);
        order.setEnable(order1.getStatus());
        orderMapper.updateById(order);
        return Result.success();
    }

    //切换12345状态
    @GetMapping("/status/order")
    public Result updateorderStatusByid(@RequestParam("id") int id, @RequestParam("status") Integer status) {
        Order order = new Order();
        order.setId(id);
        order.setStatus(status);
        orderMapper.updateById(order);
        return Result.success();
    }

    //分页查询
    @GetMapping("/page")
    public Result slectByPage(
            @RequestParam("pagesize") int pagesize,
            @RequestParam("currentpage") int currentPage,
            @RequestParam("searchtext") String SeatchText) {

        int StartPage = (currentPage - 1) * pagesize; //开始页数
        List<Order> orderList = new ArrayList<>();
        Integer total;
        if (SeatchText.isEmpty()) {
            orderList = orderMapper.slectByPage(StartPage, pagesize); //列表
            total = orderMapper.selectCount(null).intValue(); //获取总数
        } else {
            if (Integer.valueOf(SeatchText) > 100000) {
                orderList = orderMapper.slectByPageSearch(StartPage, pagesize, SeatchText); //列表
                total = orderMapper.selectCountSearch(SeatchText).intValue(); //获取总数
            } else {
                orderList = orderMapper.slectByPageSearchItem(StartPage, pagesize, SeatchText); //列表
                total = orderMapper.selectCountItem(SeatchText).intValue(); //获取总数
            }

        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", orderList);
        map.put("total", total);
        return Result.success(map);
    }
}
