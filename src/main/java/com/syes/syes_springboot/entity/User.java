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
     *  换回来了，这个是真的主键
     * 不然真的必出事
     *
     * */
    @TableId(type = IdType.INPUT)
    private String id;
    //这个是后面加的，自动增加，单不是主键
//    @TableId(type = IdType.AUTO) 这个是自增的，但是不能加这个
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
