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
 * @author eula
 * @since 2022-08-29
 */
@Getter
@Setter
@ApiModel(value = "Rootuser对象", description = "")
public class Rootuser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userid;

    private String username;

    private String password;
}
