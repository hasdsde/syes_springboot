package com.syes.syes_springboot.entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AboutDto {
    private int collect;
    private int history;
    private int comment;
    private int post;
    private int auction;
    private int orders;
    private int sold;
}
