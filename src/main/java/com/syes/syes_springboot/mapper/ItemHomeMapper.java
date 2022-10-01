package com.syes.syes_springboot.mapper;

import com.syes.syes_springboot.entity.Dto.AboutDto;
import com.syes.syes_springboot.entity.Dto.Item_homeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemHomeMapper {
    List<Item_homeDto> query(int currentPage, int pagesize);

    List<Item_homeDto> queryCollect(int startPage, int pagesize, String userid);

    List<Item_homeDto> queryHistory(int startPage, int pagesize, String userid);

    List<Item_homeDto> getPosted(int startPage, int pagesize, String userid);

    List<Item_homeDto> getAuction(int startPage, int pagesize, String userid);

    List<Item_homeDto> getBuyOrder(int startPage, int pagesize, String userid);

    List<Item_homeDto> getSoldOrder(int startPage, int pagesize, String userid);

    List<Item_homeDto> queryComment(int startPage, int pagesize, String userid);

    List<Item_homeDto> getSortList(int startPage, int pagesize, String sort);

    AboutDto selectAboutCount(String id);

    List<Item_homeDto> querySearch(int startPage, int pagesize, String text);
}
