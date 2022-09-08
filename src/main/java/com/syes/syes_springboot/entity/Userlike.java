package com.syes.syes_springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
@ApiModel(value = "Userlike对象", description = "")
public class Userlike implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userid;

    private Integer itemid;

    //下面是屎山代码
    @TableField(exist = false)
    private Integer likes;
    @TableField(exist = false)
    private Integer collects;
    @TableField(exist = false)
    private Integer comments;
    @TableField(exist = false)
    private Integer iflike;
    @TableField(exist = false)
    private Integer ifcollect;
}
