package com.syes.syes_springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syes.syes_springboot.entity.Sort;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hasdsd
 * @since 2022-09-01
 */
@Mapper
public interface SortMapper extends BaseMapper<Sort> {

    List<Sort> slectByPage(int startPage, int pagesize);

    List<Sort> slectByPageSearch(int startPage, int pagesize, String searchText);

    List<Sort> selectNsort();


    List<Sort> NodeNotNull();
}
