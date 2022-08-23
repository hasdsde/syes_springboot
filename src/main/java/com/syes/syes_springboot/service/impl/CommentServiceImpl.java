package com.syes.syes_springboot.service.impl;

import com.syes.syes_springboot.entity.Comment;
import com.syes.syes_springboot.mapper.CommentMapper;
import com.syes.syes_springboot.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品评论 服务实现类
 * </p>
 *
 * @author eula
 * @since 2022-08-23
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
