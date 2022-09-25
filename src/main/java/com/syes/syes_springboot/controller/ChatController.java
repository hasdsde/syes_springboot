package com.syes.syes_springboot.controller;

import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Dto.ChatDto;
import com.syes.syes_springboot.mapper.ChatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hasdsd
 * @since 2022-09-22
 */
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    ChatMapper chatMapper;

    //获取用户聊天历史
    @PostMapping("/his")
    public Result getChatHistory(@RequestBody ChatDto chatDto) {
        System.out.println(chatDto);
        return null;
//        int CurrentPage = Integer.parseInt(chatDto.getCurrentPage())
//        List<ChatDto> chatHistory = chatMapper.getChatHistory(chatDto.getUserid(), chatDto.getTouserid(), CurrentPage, Integer.parseInt(chatDto.getPageSize()));
//        return Result.success(chatHistory);
    }
}
