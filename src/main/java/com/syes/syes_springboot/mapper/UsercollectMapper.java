package com.syes.syes_springboot.mapper;

import com.syes.syes_springboot.entity.Comment;
import com.syes.syes_springboot.entity.Item;
import com.syes.syes_springboot.entity.Usercollect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author eula
 * @since 2022-08-26
 */
@Mapper
public interface UsercollectMapper extends BaseMapper<Usercollect> {
    // 根据userid查询
    List<Usercollect> getAllByUserId(String userid);

    int deleteusercollect(String userid , int itemid);

    // 分页
    List<Usercollect> slectByPage(int currentPage, int pagesize);

    List<Usercollect> slectByPageSearch(int currentPage, int pagesize, String searchtext);

    List<Item> getitemByUserId(String id);
}
