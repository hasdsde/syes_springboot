package com.syes.syes_springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syes.syes_springboot.entity.Roll;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 抽卡吃饭 Mapper 接口
 * </p>
 *
 * @author hasdsd
 * @since 2022-10-17
 */
@Mapper
public interface RollMapper extends BaseMapper<Roll> {

    List<Roll> selectAll();

    List<Roll> selectEnable();
}
