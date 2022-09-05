package com.syes.syes_springboot.entity;

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
 * @since 2022-09-05
 */
@Getter
@Setter
@ApiModel(value = "Userlike对象", description = "")
public class Userlike implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userid;

    private Integer itemid;
}
