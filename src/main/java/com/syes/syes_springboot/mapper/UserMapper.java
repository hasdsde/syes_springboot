package com.syes.syes_springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syes.syes_springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hasd
 * @since 2022-08-16
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> slectByPage(int currentPage, int pagesize);
}
