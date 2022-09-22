package com.syes.syes_springboot.Rcontroller;

import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Order;
import com.syes.syes_springboot.mapper.AuctionMapper;
import com.syes.syes_springboot.mapper.ItemMapper;
import com.syes.syes_springboot.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/Rorder")
public class ROrderController {
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

    //修改order
    @PutMapping("/")
    public Result updateOrder(@RequestBody Order order) {
        int i = orderMapper.updateById(order);
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

    //删除order
    @DeleteMapping("/{id}")
    public Result deleteOrder(@PathVariable("id") String id) {
        orderMapper.deleteById(id);
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
