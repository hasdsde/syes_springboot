package com.syes.syes_springboot.entity;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
    private Integer ishead;

}
