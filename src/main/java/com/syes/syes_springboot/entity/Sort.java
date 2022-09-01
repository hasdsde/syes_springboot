package com.syes.syes_springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author hasdsd
 * @since 2022-09-01
 */
@Getter
@Setter
@ApiModel(value = "Sort对象", description = "")
public class Sort implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String label;

    private String name;

    private String icon;

    private Integer pid;
}
