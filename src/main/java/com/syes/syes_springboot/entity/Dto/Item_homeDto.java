package com.syes.syes_springboot.entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item_homeDto {
    //korewa超级缝合怪
    private String url;
    private int id;
    private String title;
    private String nickname;
    private String avatar;
    private double price;
    private LocalDateTime createtime;
    private LocalDateTime updatatime;
    private int collectionnum;
    private int clicknum;
    private int grade;
    private int onsale;
    private int itemprice;
    private int status;
}
