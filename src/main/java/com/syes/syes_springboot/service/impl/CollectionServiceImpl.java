package com.syes.syes_springboot.service.impl;

import com.syes.syes_springboot.entity.Collection;
import com.syes.syes_springboot.mapper.CollectionMapper;
import com.syes.syes_springboot.service.ICollectionService;
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
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements ICollectionService {

}
