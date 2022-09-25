package com.syes.syes_springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syes.syes_springboot.entity.Chat;
import com.syes.syes_springboot.entity.Dto.ChatDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hasdsd
 * @since 2022-09-22
 */
@Mapper
public interface ChatMapper extends BaseMapper<Chat> {

    List<ChatDto> getChatHistory(String userid, String touserid, int currentPage, int pageSize);
}
