package com.syes.syes_springboot.mapper;

import com.syes.syes_springboot.entity.Rollimg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hasdsd
 * @since 2022-09-02
 */
@Mapper
public interface RollimgMapper extends BaseMapper<Rollimg> {

    List<Rollimg> queryrollimg(int id);


    int deleterollimg(int id, String url);

    void addrollimg(String url);

    List<Rollimg> queryallarollimg();
}
