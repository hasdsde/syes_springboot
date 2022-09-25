package com.syes.syes_springboot.entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatDto {
    private String userid;

    private String touserid;

    private String content;

    private String avatar;

    private String nickname;

    private LocalDateTime createtime;

    private Integer pageSize;

    private Integer currentPage;
}
