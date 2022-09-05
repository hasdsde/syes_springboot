package com.syes.syes_springboot.mapper;

import com.syes.syes_springboot.entity.Auction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hasdsd
 * @since 2022-09-05
 */
@Mapper
public interface AuctionMapper extends BaseMapper<Auction> {

    void insert(int itemid, String userid, int price);

    int updateById(int price,int id);
}
