package com.syes.syes_springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.File;
import com.syes.syes_springboot.entity.Item;
import com.syes.syes_springboot.entity.Itempic;
import com.syes.syes_springboot.entity.User;
import com.syes.syes_springboot.mapper.FileMapper;
import com.syes.syes_springboot.mapper.ItemMapper;
import com.syes.syes_springboot.mapper.ItempicMapper;
import com.syes.syes_springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
    private FileMapper fileMapper;
    @Resource
    private ItempicMapper itempicMapper;
    @Resource
    private ItemMapper itemMapper;

    @Resource
    private UserMapper userMapper;

    @Value("${my.file-config.uploadPath}")
    private String uploadPath;

    @Value("${my.file-config.downloadPath}")
    private String downloadPath;

    @GetMapping("/id")
    public Result itemById(@RequestParam("itemid") int itemid) {

        // 获取item
        Item item = itemMapper.selectById(itemid);

        // 修改userid
        String realUserId = item.getUserid();
        String userid = item.getUserid().substring(2, 4);
        item.setUserid(userid);

        // user搞里头
        User user = userMapper.selectById(realUserId);
        user.setId(userid);
        item.setUser(user);


        // 获取itempic表数据
        LambdaQueryWrapper<Itempic> picWrapper = new LambdaQueryWrapper<>();
        picWrapper.eq(Itempic::getItemid, itemid);
        List<Itempic> itempicList = itempicMapper.selectList(picWrapper);

        for (Itempic itempic : itempicList) {
            File file = fileMapper.selectById(itempic.getPicid());
            item.addImg(file);
        }

        return Result.success(item);
    }

    // 新建item附带图片
    @PostMapping("/uploadAll")
    public Result InsertItem(/*@RequestPart Item item,*/
            @RequestParam("title") String title,
            @RequestParam("userid") String userid,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam("piclist") int[] piclist
    ) {
        //上传物品并获取id
        System.out.println(Arrays.toString(piclist));
        Item item = new Item();
        item.setTitle(title);
        item.setUserid(userid);
        item.setDescription(description);
        item.setPrice(price);
        Integer id = (Integer) SaveItem(item).getData();
        System.out.println("id==" + id);
        //上传的图片物品关联数据库
        for (int i : piclist) {
            Itempic itempic = new Itempic();
            //如果表内没有这个物品的图片，那么设置第一张图为首页
            QueryWrapper<Itempic> wrapper = new QueryWrapper<>();
            wrapper.eq("itemid", id);
            if (itempicMapper.selectCount(wrapper) == 0) {
                itempic.setIshead(1);
            }
            itempic.setItemid(id);
            itempic.setPicid(i);
            itempicMapper.insert(itempic);
        }
        return Result.success("上传成功");
    }

    //新建item
    @PostMapping("/")
    public Result SaveItem(@RequestBody Item item) {
        item.setCreatetime(LocalDateTime.now());
        int insert = itemMapper.insert(item);
        return Result.success(item.getId());
    }

    //修改item
    @PutMapping("/")
    public Result updateItem(@RequestBody Item item) {
        item.setCreatetime(LocalDateTime.now());
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
    public Result slectByPage(
            @RequestParam("pagesize") int pagesize,
            @RequestParam("currentpage") int currentPage,
            @RequestParam("searchtext") String SearchText
    ) {
        Integer total;
        int StartPage = (currentPage - 1) * pagesize; //开始页数
        List<Item> itemList = new ArrayList<>();
        QueryWrapper<Item> wrapper = new QueryWrapper<>();
        wrapper.eq("userid", SearchText);
        if (SearchText.isEmpty()) {
            itemList = itemMapper.slectByPage(StartPage, pagesize); //列表
            total = itemMapper.selectCount(null).intValue(); //获取总数
        } else {
            itemList = itemMapper.slectByPageSearch(StartPage, pagesize, SearchText); //列表
            total = itemMapper.selectCount(wrapper).intValue(); //获取总数
        }
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
