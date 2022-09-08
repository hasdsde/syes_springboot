package com.syes.syes_springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.syes.syes_springboot.Utils.IdUtil;
import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Item;
import com.syes.syes_springboot.entity.Usercollect;
import com.syes.syes_springboot.mapper.ItemMapper;
import com.syes.syes_springboot.mapper.UsercollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户收藏
 * </p>
 *
 * @author eula
 * @since 2022-08-26
 */
@RestController
@RequestMapping("/usercollect")
public class UsercollectController {

    @Autowired
    UsercollectMapper usercollectMapper;

    @Resource
    ItemMapper itemMapper;

    @GetMapping("/queryByUserId")
    public Result queryByUserId(HttpServletRequest request,
                                @RequestParam("cp") int cp,
                                @RequestParam("ps") int ps) {
        // 根据request获取userid
        String userId = IdUtil.getId(request);

        // 初始化
        Map<String, Object> map = new HashMap<>();
        Page<Usercollect> page = new Page<>(cp, ps);

        // 查询收藏物品
        QueryWrapper<Usercollect> wrapper = new QueryWrapper<>();
        wrapper.eq("userid", userId);
        Page<Usercollect> lists = usercollectMapper.selectPage(page, wrapper);
        Long count = usercollectMapper.selectCount(wrapper);
        // 获取物品详情
        for (Usercollect list : lists.getRecords()) {
            // 获取itemid
            Integer itemid = list.getItemid();
            // 查询数据
            Item item = itemMapper.selectById(itemid);
            list.setItem(item);
        }

        map.put("lists", lists);
        map.put("count", count);

        return Result.success(map);
    }

    //改变情况
    @GetMapping("/change")
    public Result ChangeStatus(@RequestParam("itemid") int itemid, HttpServletRequest request) {
        String userid = IdUtil.getId(request);
        QueryWrapper<Usercollect> wrapper = new QueryWrapper<>();
        wrapper.eq("itemid", itemid);
        wrapper.eq("userid", userid);
        Long aLong = usercollectMapper.selectCount(wrapper);
        if (aLong == 1) {
            usercollectMapper.delete(wrapper);
        }
        if (aLong == 0) {
            Usercollect userlike = new Usercollect();
            userlike.setItemid(itemid);
            userlike.setUserid(userid);
            usercollectMapper.insert(userlike);
        }
        return Result.success();
    }

    @GetMapping("/queryByItemId")
    public Result queryByItemId(@RequestParam("itemid") int itemid) {
        // 返回收藏总数wq
        QueryWrapper<Usercollect> wrapper = new QueryWrapper<>();
        wrapper.eq("itemid", itemid);
        Long count = usercollectMapper.selectCount(wrapper);
        return Result.success(count);
    }

    //新建
    @PostMapping("/")
    public Result SaveItem(@RequestBody Usercollect usercollect) {
        usercollectMapper.insert(usercollect);
        return Result.success();
    }

    //删除
    @DeleteMapping("/{userid}/{itemid}")
    public Result deleteItem(@PathVariable("userid") String userid, @PathVariable("itemid") int itemid) {
        usercollectMapper.deleteusercollect(userid, itemid);
        return Result.success();
    }

    //查询收藏人的数量
    @GetMapping("/itemid/{itemid}")
    public long queryByItem(@PathVariable("itemid") int itemid) {
        QueryWrapper<Usercollect> wrapper = new QueryWrapper<>();
        wrapper.eq("itemid", itemid);
        return usercollectMapper.selectCount(wrapper);
    }

    //根据userid查询收藏物品
    @GetMapping("/userid/{userid}")
    public Result getCommentByUserId(@PathVariable String userid) {
        QueryWrapper<Usercollect> wrapper = new QueryWrapper<>();
        wrapper.eq("userid", userid);
        List<Usercollect> res = usercollectMapper.selectList(wrapper);
        return Result.success(res);
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
        List<Usercollect> usercollectList = new ArrayList<>();
        QueryWrapper<Usercollect> wrapper = new QueryWrapper<>();
        wrapper.eq("userid", SearchText);
        if (SearchText.isEmpty()) {
            usercollectList = usercollectMapper.slectByPage(StartPage, pagesize); //列表
            total = usercollectMapper.selectCount(null).intValue(); //获取总数
        } else {
            usercollectList = usercollectMapper.slectByPageSearch(StartPage, pagesize, SearchText); //列表
            total = usercollectMapper.selectCount(wrapper).intValue(); //获取总数
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", usercollectList);
        map.put("total", total);
        return Result.success(map);
    }
}
