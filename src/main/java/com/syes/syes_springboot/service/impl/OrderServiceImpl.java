package com.syes.syes_springboot.service.impl;

import com.syes.syes_springboot.entity.Order;
import com.syes.syes_springboot.mapper.OrderMapper;
import com.syes.syes_springboot.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author eula
 * @since 2022-08-26
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
