package com.syes.syes_springboot.mapper;

import com.syes.syes_springboot.entity.Dto.Item_homeDto;

import java.util.List;

public interface ItemHomeMapper {
    List<Item_homeDto> query(int currentPage, int pagesize);
}
