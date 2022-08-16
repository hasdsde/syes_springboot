package com.syes.syes_springboot.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
<<<<<<< HEAD
import lombok.Getter;
import lombok.Setter;
=======

import lombok.*;
>>>>>>> fd88836e6262aa9d69e70528e22687db4a9722cf

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
<<<<<<< HEAD
=======
@Data
@AllArgsConstructor
@NoArgsConstructor
>>>>>>> fd88836e6262aa9d69e70528e22687db4a9722cf
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

<<<<<<< HEAD

=======
>>>>>>> fd88836e6262aa9d69e70528e22687db4a9722cf
    private String number;

    private String nickname;

    private String realname;

    private String password;

    private String avatar;

    private LocalDateTime createtime;

    private String phone;


}
