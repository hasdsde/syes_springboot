package com.syes.syes_springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Comment;
import com.syes.syes_springboot.entity.Comment;
import com.syes.syes_springboot.mapper.CommentMapper;
import com.syes.syes_springboot.mapper.CommentMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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

    // 根据itemid查评论
    @GetMapping("/item/{id}")
    public Result getCommentByItemId(@PathVariable int id) {
        List<Comment> res = mapper.getCommentByItemId(id);
        return Result.success(res);
    }

    // region 常规CRUD+分页
    //根据id查询
    @GetMapping("/{id}")
    public Result getCommentById(@PathVariable("id") int id) {
        Comment comments = mapper.getCommentById(id);
        return Result.success(comments);
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
    public Result slectByPage(@RequestParam("pagesize") int pagesize, @RequestParam("currentpage") int currentPage) {

        Integer total = mapper.selectCount(null).intValue(); //获取总数
        int StartPage = (currentPage - 1) * pagesize; //开始页数
        List<Comment> commentList = mapper.slectByPage(StartPage, pagesize); //列表

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", commentList);
        map.put("total", total);
        return Result.success(map);
    }
    // endregion
}
