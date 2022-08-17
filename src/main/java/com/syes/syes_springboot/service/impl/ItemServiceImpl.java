package com.syes.syes_springboot.service.impl;

import com.syes.syes_springboot.entity.Item;
import com.syes.syes_springboot.mapper.ItemMapper;
import com.syes.syes_springboot.service.IItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author test
 * @since 2022-08-17
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

}
