package com.syes.syes_springboot.controller;

import com.syes.syes_springboot.Utils.IdUtil;
import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Dto.AboutDto;
import com.syes.syes_springboot.entity.Dto.Item_homeDto;
import com.syes.syes_springboot.mapper.ChatMapper;
import com.syes.syes_springboot.mapper.ItemHomeMapper;
import com.syes.syes_springboot.mapper.OrderMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/itemHome")
public class ItemHomeController {
    @Resource
    ItemHomeMapper itemHomeMapper;
    @Resource
    ChatMapper chatMapper;
    @Resource
    OrderMapper orderMapper;

    //首页翻页
    @GetMapping("/page")
    public Result query(@RequestParam("pagesize") int pagesize, @RequestParam("currentpage") int currentpage) {
        int StartPage = (currentpage - 1) * pagesize;
        List<Item_homeDto> items = itemHomeMapper.query(StartPage, pagesize);
        return Result.success(items);
    }

    //检测是否有未读消息和进行中的交易
    @GetMapping("/Message")
    public Result CheckMessage(HttpServletRequest request) {
        String id = IdUtil.getId(request);
        HashMap<String, Integer> map = new HashMap<>();
        int message = chatMapper.selectUnreadCount(id);
        int order = orderMapper.selectOrderCount(id);
        map.put("mCount", message);
        map.put("oCount", order);
        return Result.success(map);
    }

    //统计关于页面各个按钮内数量
    @GetMapping("/About")
    public Result GetAboutCount(HttpServletRequest request) {
        String id = IdUtil.getId(request);
        AboutDto aboutDto = itemHomeMapper.selectAboutCount(id);
        return Result.success(aboutDto);
    }
}

