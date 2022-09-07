package com.syes.syes_springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syes.syes_springboot.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 商品评论 Mapper 接口
 * </p>
 *
 * @author eula
 * @since 2022-08-23
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    // 根据userid查询
    List<Comment> getCommentByUserId(String userid);

    // 根据itemid查询
    List<Comment> getCommentByItemId(int itemid);

    // 根据id查询
    Comment getCommentById(int id);

//    // 修改
//    int updateComment(Comment comment);
//
//    // 删除
//    int deleteComment(Comment comment);

    // 分页
    List<Comment> slectByPage(int currentPage, int pagesize);

    List<Comment> slectByPageSearch(int startPage, int pagesize, String seatchText);

    List<Comment> slectByPageSearchItem(int startPage, int pagesize, String seatchText);


    Integer selectCountSearch(String seatchText);

    Integer selectCountItem(String seatchText);

    List<Comment> selectFcomm(int itemid);

    List<Comment> selectEComment(int commentid);
}
