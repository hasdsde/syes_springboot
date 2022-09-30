package com.syes.syes_springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syes.syes_springboot.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author eula
 * @since 2022-08-26
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    List<Order> slectByPage(int startPage, int pagesize);

    List<Order> slectByPageSearch(int startPage, int pagesize, String seatchText);

    Integer selectCountSearch(String seatchText);

    List<Order> slectByPageSearchItem(int startPage, int pagesize, String seatchText);

    Integer selectCountItem(String seatchText);

    int selectOrderCount(String id);
}
