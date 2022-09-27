package com.syes.syes_springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author hasdsd
 * @since 2022-09-22
 */
@Getter
@Setter
@ApiModel(value = "Chat对象", description = "")
@ToString
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userid;

    private String touserid;

    private String content;

    private LocalDateTime createtime;

    private Boolean enable;

    private int readed;//是否已读，默认为1，已读
}
