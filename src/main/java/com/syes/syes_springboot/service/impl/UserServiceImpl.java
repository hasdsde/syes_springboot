package com.syes.syes_springboot.service.impl;

import com.syes.syes_springboot.entity.User;
import com.syes.syes_springboot.mapper.UserMapper;
import com.syes.syes_springboot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hasd
 * @since 2022-08-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
