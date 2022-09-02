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
 * @since 2022-09-02
 */
@Getter
@Setter
@ApiModel(value = "Rollimg对象", description = "")
public class Rollimg implements Serializable {

    private static final long serialVersionUID = 1L;

    private String url;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
}
