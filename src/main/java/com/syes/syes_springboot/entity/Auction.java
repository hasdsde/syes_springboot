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
 *
 * </p>
 *
 * @author hasdsd
 * @since 2022-09-05
 */
@Getter
@Setter
@ApiModel(value = "Auction对象", description = "")
public class Auction implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userid;

    private Integer itemid;

    private Integer price;

    private LocalDateTime time;

    //该属性在数据库中不存在
    @TableField(exist = false)
    private String avatar;
    //该属性在数据库中不存在
    @TableField(exist = false)
    private String username;
    //该属性在数据库中不存在
    @TableField(exist = false)
    private String title;
}
