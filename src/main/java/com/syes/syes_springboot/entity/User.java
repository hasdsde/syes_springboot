package com.syes.syes_springboot.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;


    private String number;

    private String nickname;

    private String realname;

    private String password;

    private String avatar;

    private LocalDateTime createtime;

    private String phone;


}
