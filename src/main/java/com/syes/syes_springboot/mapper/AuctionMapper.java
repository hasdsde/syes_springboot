package com.syes.syes_springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syes.syes_springboot.entity.Auction;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hasdsd
 * @since 2022-09-05
 */
@Mapper
public interface AuctionMapper extends BaseMapper<Auction> {


    int setStatus(String itemid, int id);
}
