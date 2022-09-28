package com.syes.syes_springboot.entity.Dto;

import java.io.Serializable;

public class Chat_info implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nickname;

    private String avatar;

    private String content;

    private int count;

    private int readed;//是否已读，默认为1，已读
}
