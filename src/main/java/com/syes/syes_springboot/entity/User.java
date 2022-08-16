package com.syes.syes_springboot.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author hasd
 * @since 2022-08-16
 */
@Getter
@Setter
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("学号")
    private String number;

    private String nickname;

    @ApiModelProperty("真实姓名")
    private String realname;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("用户头像地址")
    private String avatar;

    @ApiModelProperty("创建日期")
    private LocalDateTime createtime;

    @ApiModelProperty("手机号")
    private String phone;


}
