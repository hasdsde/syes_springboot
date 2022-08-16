package com.syes.syes_springboot.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.syes.syes_springboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hasd
 * @since 2022-08-16
 */
public interface IUserService extends IService<User> {

    List<User> queryAllUser();

}
