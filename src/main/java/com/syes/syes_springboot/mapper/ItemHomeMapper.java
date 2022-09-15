package com.syes.syes_springboot.mapper;

import com.syes.syes_springboot.entity.Dto.Item_homeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemHomeMapper {
    List<Item_homeDto> query(int currentPage, int pagesize);

    List<Item_homeDto> queryCollect(int startPage, int pagesize, String userid);

    List<Item_homeDto> queryHistory(int startPage, int pagesize, String userid);

    List<Item_homeDto> getPosted(int startPage, int pagesize, String userid);
}
