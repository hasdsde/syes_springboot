package com.syes.syes_springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Item;
import com.syes.syes_springboot.mapper.ItemMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author test
 * @since 2022-08-17
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @Resource
    ItemMapper itemMapper;

    // 根据id查询
    @GetMapping("/{id}")
    public Result getItemById(@PathVariable int id) {
        Item item = itemMapper.selectById(id);
        return Result.success(item);
    }

    //新建item
    @PostMapping("/")
    public Result SaveItem(@RequestBody Item item) {
        item.setCreatetime(LocalDateTime.now());
        int insert = itemMapper.insert(item);
        return Result.success();
    }

    //修改item
    @PutMapping("/")
    public Result updateItem(@RequestBody Item item) {
        int i = itemMapper.updateById(item);
        return Result.success();
    }

    //删除item
    @DeleteMapping("/{id}")
    public Result deleteItem(@PathVariable("id") String id) {
        itemMapper.deleteById(id);
        return Result.success();
    }

    //分页查询
    @GetMapping("/page")
    public Result slectByPage(@RequestParam("pagesize") int pagesize, @RequestParam("currentpage") int currentPage) {

        QueryWrapper wrapper = new QueryWrapper();
        Integer total = itemMapper.selectCount(wrapper).intValue(); //获取总数
        int StartPage = (currentPage - 1) * pagesize; //开始页数
        List<Item> itemList = itemMapper.slectByPage(StartPage, pagesize); //列表
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", itemList);
        map.put("total", total);
        return Result.success(map);
    }

    //切换上架下架状态
    @GetMapping("/status")
    public Result changeByStatus(@RequestParam("id") Integer id, @RequestParam("status") Boolean status) {
        Item item = new Item();
        item.setId(id);
        item.setOnsale(status);
        itemMapper.updateById(item);
        return Result.success();
    }
}
