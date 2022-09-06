package com.syes.syes_springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syes.syes_springboot.entity.File;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author test
 * @since 2022-08-17
 */
@Mapper
public interface FileMapper extends BaseMapper<File> {

    // 分页查询
    List<File> slectByPage(int currentPage, int pagesize);

    List<File> slectByPageSearch(int startPage, int pagesize, String searchText);

    List<File> selectimgByid(int itemid);
}
