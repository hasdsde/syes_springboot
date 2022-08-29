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
 * @author eula
 * @since 2022-08-29
 */
@Getter
@Setter
@ApiModel(value = "Rootuser对象", description = "")
public class Rootuser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

    private String password;
}
