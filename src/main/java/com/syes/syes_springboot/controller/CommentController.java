package com.syes.syes_springboot.controller;

import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Comment;
import com.syes.syes_springboot.mapper.CommentMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 商品评论 前端控制器
 * </p>
 *
 * @author eula
 * @since 2022-08-23
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    CommentMapper mapper;

    // 根据userid查评论
    @GetMapping("/user/{id}")
    public Result getCommentByUserId(@PathVariable String id) {
        List<Comment> res = mapper.getCommentByUserId(id);
        return Result.success(res);
    }

    //根据物品id获取父级评论
    @GetMapping("/f")
    public Result getCommentF(@RequestParam("itemid") int itemid) {
        List<Comment> list = mapper.selectFcomm(itemid);
        return Result.success(list);
    }


    //新建评论
    @PostMapping("/")
    public Result SaveComment(@RequestBody Comment comment) {
        comment.setCreatetime(LocalDateTime.now());
        int insert = mapper.insert(comment);
        return Result.success();
    }

    //修改评论
    @PutMapping("/")
    public Result updateComment(@RequestBody Comment comment) {
        int i = mapper.updateById(comment);
        return Result.success();
    }

    //删除评论
    @DeleteMapping("/{id}")
    public Result deleteComment(@PathVariable("id") String id) {
        mapper.deleteById(id);
        return Result.success(id);
    }


    //分页查询
    @GetMapping("/page")
    public Result slectByPage(
            @RequestParam("pagesize") int pagesize,
            @RequestParam("currentpage") int currentPage,
            @RequestParam("searchtext") String SeatchText) {


        int StartPage = (currentPage - 1) * pagesize; //开始页数
        List<Comment> commentList = new ArrayList<>();
        Integer total;
        if (SeatchText.isEmpty()) {
            commentList = mapper.slectByPage(StartPage, pagesize); //列表
            total = mapper.selectCount(null).intValue(); //获取总数
        } else {
            if (Integer.valueOf(SeatchText) > 100000) {
                commentList = mapper.slectByPageSearch(StartPage, pagesize, SeatchText); //列表
                total = mapper.selectCountSearch(SeatchText).intValue(); //获取总数
            } else {
                commentList = mapper.slectByPageSearchItem(StartPage, pagesize, SeatchText); //列表
                total = mapper.selectCountItem(SeatchText).intValue(); //获取总数
            }

        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", commentList);
        map.put("total", total);
        return Result.success(map);
    }
    // endregion
}
