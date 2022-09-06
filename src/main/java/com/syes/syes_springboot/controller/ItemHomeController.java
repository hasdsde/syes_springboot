package com.syes.syes_springboot.controller;

import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.Dto.Item_homeDto;
import com.syes.syes_springboot.mapper.ItemHomeMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/itemHome")
public class ItemHomeController {
    @Resource
    ItemHomeMapper itemHomeMapper;

    @GetMapping("/page")
    public Result query(@RequestParam("pagesize") int pagesize, @RequestParam("currentpage") int currentpage) {
        int StartPage = (currentpage - 1) * pagesize;
        List<Item_homeDto> items = itemHomeMapper.query(StartPage, pagesize);
        return Result.success(items);
    }
//  这个屎山代码不要删，要提醒其他人
//    public List<Item_homeDto> check( int pagesize, int currentpage){
//        List<Item_hogitmeDto> item1 = new ArrayList<>();
//        boolean st = true;
//        while (true) {
//            int StartPage = (currentpage - 1) * pagesize;
//            List<Item_homeDto> items = itemHomeMapper.query(StartPage, pagesize);
//
//            if (items.size() < pagesize) {
//                for (int i = 1; i < items.size(); i++) {
//                    while (i < items.size() && items.get(i).getId() == items.get(i - 1).getId()) i++;
//                    if(i <= items.size()) item1.add(items.get(i - 1));
//                    if (item1.size() == pagesize) {
//                        st = false;
//                        break;
//                    }
//                }
//                st = false;
//            } else {
//                for (int i = 1; i < items.size(); i++) {
//                    while (i < items.size() && items.get(i).getId() == items.get(i - 1).getId()) i++;
//                    if(i <= items.size()) item1.add(items.get(i - 1));
//                    if (item1.size() == pagesize) {
//                        st = false;
//                        break;
//                    }
//                }
//
//            }
//            if (!st) break;
//            currentpage++;
//        }
//        return item1;
//    }
}

