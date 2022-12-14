package com.syes.syes_springboot.controller;

import com.syes.syes_springboot.Utils.IdUtil;
import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Dto.Item_homeDto;
import com.syes.syes_springboot.mapper.ItemHomeMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/about")
public class AboutController {
    @Resource
    ItemHomeMapper itemHomeMapper;

    //我的收藏
    @GetMapping("/collect")
    public Result GetCollectByUser(
            @RequestParam("pagesize") int pagesize,
            @RequestParam("currentpage") int currentpage,
            HttpServletRequest request) {
        String userid = IdUtil.getId(request);
        int StartPage = (currentpage - 1) * pagesize;
        List<Item_homeDto> items = itemHomeMapper.queryCollect(StartPage, pagesize, userid);
        return Result.success(items);
    }

    //历史记录
    @GetMapping("/history")
    public Result GetHistoryByUser(
            @RequestParam("pagesize") int pagesize,
            @RequestParam("currentpage") int currentpage,
            HttpServletRequest request) {
        String userid = IdUtil.getId(request);
        int StartPage = (currentpage - 1) * pagesize;
        List<Item_homeDto> items = itemHomeMapper.queryHistory(StartPage, pagesize, userid);
        return Result.success(items);
    }

    //我的评论
    @GetMapping("/comment")
    public Result GetComment(@RequestParam("pagesize") int pagesize, @RequestParam("currentpage") int currentpage, HttpServletRequest request) {
        String userid = IdUtil.getId(request);
        int StartPage = (currentpage - 1) * pagesize;
        List<Item_homeDto> commenets = itemHomeMapper.queryComment(StartPage, pagesize, userid);
        return Result.success(commenets);
    }

    //我发布的
    @GetMapping("/posted")
    public Result GetIPosted(HttpServletRequest request,
                             @RequestParam("pagesize") int pagesize,
                             @RequestParam("currentpage") int currentpage) {
        int StartPage = (currentpage - 1) * pagesize;
        String userid = IdUtil.getId(request);
        List<Item_homeDto> posted = itemHomeMapper.getPosted(StartPage, pagesize, userid);
        return Result.success(posted);
    }

    //我的出价
    @GetMapping("/auction")
    public Result GetAuction(HttpServletRequest request,
                             @RequestParam("pagesize") int pagesize,
                             @RequestParam("currentpage") int currentpage) {
        int StartPage = (currentpage - 1) * pagesize;
        String userid = IdUtil.getId(request);
        List<Item_homeDto> posted = itemHomeMapper.getAuction(StartPage, pagesize, userid);
        return Result.success(posted);

    }

    //我买入的
    @GetMapping("/BuyOrder")
    public Result GetBuyOrder(HttpServletRequest request,
                              @RequestParam("pagesize") int pagesize,
                              @RequestParam("currentpage") int currentpage) {
        int StartPage = (currentpage - 1) * pagesize;
        String userid = IdUtil.getId(request);
        List<Item_homeDto> posted = itemHomeMapper.getBuyOrder(StartPage, pagesize, userid);
        return Result.success(posted);

    }

    //我卖出的
    @GetMapping("/SoldOrder")
    public Result GetSoldOrder(HttpServletRequest request,
                               @RequestParam("pagesize") int pagesize,
                               @RequestParam("currentpage") int currentpage) {
        int StartPage = (currentpage - 1) * pagesize;
        String userid = IdUtil.getId(request);
        List<Item_homeDto> posted = itemHomeMapper.getSoldOrder(StartPage, pagesize, userid);
        return Result.success(posted);

    }

    //分类查询
    @GetMapping("/sort")
    public Result GetSort(
            @RequestParam("sort") String sort,
            @RequestParam("pagesize") int pagesize,
            @RequestParam("currentpage") int currentpage) {
        int StartPage = (currentpage - 1) * pagesize;
        List<Item_homeDto> posted = itemHomeMapper.getSortList(StartPage, pagesize, sort);
        return Result.success(posted);
    }
}
