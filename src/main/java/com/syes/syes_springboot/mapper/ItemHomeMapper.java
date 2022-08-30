package com.syes.syes_springboot.mapper;

import com.syes.syes_springboot.entity.Dto.Item_homeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ItemHomeMapper {
    List<Item_homeDto> query(int currentPage, int pagesize);
}
