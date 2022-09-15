package com.syes.syes_springboot.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户浏览历史
 * </p>
 *
 * @author hasdsd
 * @since 2022-09-15
 */
@Getter
@Setter
@ApiModel(value = "Userhistory对象", description = "用户浏览历史")
public class Userhistory implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userid;

    private Integer itemid;

    private LocalDateTime createtime;
}
