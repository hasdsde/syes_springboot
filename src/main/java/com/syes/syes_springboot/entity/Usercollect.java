package com.syes.syes_springboot.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author eula
 * @since 2022-08-26
 */
@Getter
@Setter
@ApiModel(value = "Usercollect对象", description = "")
public class Usercollect implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userid;

    private Integer itemid;

    @TableField(exist = false)
    private Item item;
}
