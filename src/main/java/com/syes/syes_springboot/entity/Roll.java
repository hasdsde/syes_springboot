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
 * 抽卡吃饭
 * </p>
 *
 * @author hasdsd
 * @since 2022-10-17
 */
@Getter
@Setter
@ApiModel(value = "Roll对象", description = "抽卡吃饭")
public class Roll implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer ranks;

    private String sort;

    private Integer level;

    private String p;
}
