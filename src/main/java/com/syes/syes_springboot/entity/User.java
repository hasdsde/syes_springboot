package com.syes.syes_springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;


    /*
     *  主键策略，这个不是主键，曾经是主键，单现在不是了
     *为了还能继续用plus，所以还得吧这个方程主键
     * */
    @TableId(type = IdType.INPUT)
    private String id;
    //这个才是真正的主键，自动增加的主键
    private int infoid;

    private String nickname;

    private String realname;

    private String password;

    private String avatar;

    private LocalDateTime createtime;

    private String phone;


    //获取用户时，不会返回密码
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
