package com.syes.syes_springboot.mapper;

import com.syes.syes_springboot.entity.Item;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syes.syes_springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author test
 * @since 2022-08-17
 */
@Mapper
public interface ItemMapper extends BaseMapper<Item> {

    // 分页
    List<Item> slectByPage(int currentPage, int pagesize);
}
