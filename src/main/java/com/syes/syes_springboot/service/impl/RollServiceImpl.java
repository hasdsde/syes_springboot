package com.syes.syes_springboot.service.impl;

import com.syes.syes_springboot.entity.Roll;
import com.syes.syes_springboot.mapper.RollMapper;
import com.syes.syes_springboot.service.IRollService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 抽卡吃饭 服务实现类
 * </p>
 *
 * @author hasdsd
 * @since 2022-10-17
 */
@Service
public class RollServiceImpl extends ServiceImpl<RollMapper, Roll> implements IRollService {

}
