package com.syes.syes_springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @author eula
 * @since 2022-08-26
 */
@Getter
@Setter
@ApiModel(value = "Order对象", description = "")
@TableName("userorder")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userid;

    private Integer itemid;

    private Integer enable;

    private Integer status;

    private LocalDateTime createtime;
}
