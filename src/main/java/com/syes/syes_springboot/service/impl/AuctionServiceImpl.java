package com.syes.syes_springboot.service.impl;

import com.syes.syes_springboot.entity.Auction;
import com.syes.syes_springboot.mapper.AuctionMapper;
import com.syes.syes_springboot.service.IAuctionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hasdsd
 * @since 2022-09-05
 */
@Service
public class AuctionServiceImpl extends ServiceImpl<AuctionMapper, Auction> implements IAuctionService {

}
