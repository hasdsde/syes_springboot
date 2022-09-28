package com.syes.syes_springboot.entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Chat_info implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nickname;

    private String avatar;

    private String cont;

    private int count;

    private int readed;//是否已读，默认为1，已读
}
