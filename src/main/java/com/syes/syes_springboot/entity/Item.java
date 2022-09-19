package com.syes.syes_springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author test
 * @since 2022-08-17
 */
@Getter
@Setter
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键自增，不行再改
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private LocalDateTime createtime;

    private String description;

    private Boolean onsale;

    private Double price;

    private String userid;

    private int visited;
    private String sort;

    @TableField(exist = false)
    private String nickname;
    @TableField(exist = false)
    private String avatar;
    @TableField(exist = false)
    private String grade;
}
