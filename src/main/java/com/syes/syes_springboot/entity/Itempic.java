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
 * @since 2022-09-03
 */
@Getter
@Setter
@ApiModel(value = "Itempic对象", description = "")
public class Itempic implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer picid;

    private Integer itemid;
}
