package com.syes.syes_springboot.controller;

import com.syes.syes_springboot.Utils.IdUtil;
import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Dto.ChatDto;
import com.syes.syes_springboot.entity.Dto.Chat_info;
import com.syes.syes_springboot.entity.User;
import com.syes.syes_springboot.mapper.ChatMapper;
import com.syes.syes_springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Autowired
    UserMapper userMapper;


    //获取用户聊天历史
    @PostMapping("/his")
    public Result getChatHistory(@RequestBody ChatDto chatDto) {
        int CurrentPage = (chatDto.getCurrentPage() - 1) * chatDto.getPageSize();
        List<ChatDto> chatHistory = chatMapper.getChatHistory(chatDto.getUserid(), chatDto.getTouserid(), CurrentPage, chatDto.getPageSize());
        return Result.success(chatHistory);
    }

    //获取未读消息和发送者信息
    @GetMapping("/new")
    public Result getNewinfo(HttpServletRequest request) {
        String id = IdUtil.getId(request);
        User user = userMapper.selectById(id);
        List<Chat_info> chat_infos = chatMapper.selectNewsInfo(String.valueOf(user.getInfoid()));
        return Result.success(chat_infos);
    }

    //获取历史消息
    @GetMapping("/ChatHis")
    public Result getChatHis(@RequestParam("CurrentPage") int CurrentPage, @RequestParam("PageSize") int PageSize, HttpServletRequest request) {
        String id = IdUtil.getId(request);
        User user = userMapper.selectById(id);
        int currentPage = (CurrentPage - 1) * PageSize;
        //这个Sql写的就离谱，不知道怎么写的，反正把历史消息和新消息分开了，哈↑哈↑哈↑哈↑哈peko
        List<Chat_info> chat_infos = chatMapper.selectChatHis(currentPage, PageSize, String.valueOf(user.getInfoid()));
        return Result.success(chat_infos);
    }
}
