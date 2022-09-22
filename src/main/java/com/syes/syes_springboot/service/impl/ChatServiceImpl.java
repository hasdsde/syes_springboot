package com.syes.syes_springboot.service.impl;

import com.syes.syes_springboot.entity.Chat;
import com.syes.syes_springboot.mapper.ChatMapper;
import com.syes.syes_springboot.service.IChatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hasdsd
 * @since 2022-09-22
 */
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements IChatService {

}
