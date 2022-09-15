package com.syes.syes_springboot.service.impl;

import com.syes.syes_springboot.entity.Userhistory;
import com.syes.syes_springboot.mapper.UserhistoryMapper;
import com.syes.syes_springboot.service.IUserhistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户浏览历史 服务实现类
 * </p>
 *
 * @author hasdsd
 * @since 2022-09-15
 */
@Service
public class UserhistoryServiceImpl extends ServiceImpl<UserhistoryMapper, Userhistory> implements IUserhistoryService {

}
