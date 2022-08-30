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
    private String itemid;
    private String nickname;
    private String avatar;
    private int price;
    private LocalDateTime createtime;
    private int collectionnum;
    private int clicknum;
}
