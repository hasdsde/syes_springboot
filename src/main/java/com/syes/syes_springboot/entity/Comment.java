package com.syes.syes_springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品评论
 * </p>
 *
 * @author eula
 * @since 2022-08-23
 */
@Getter
@Setter
@ApiModel(value = "Comment对象", description = "商品评论")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    //物品id
    private Integer itemid;
    //上传者id
    private String userid;
    //创建时间
    private LocalDateTime createtime;

    //评论内容
    private String content;

    //在哪个评论之下
    private Integer tocommentid;

    // 用户
    @TableField(exist = false)
    private User user;
}
