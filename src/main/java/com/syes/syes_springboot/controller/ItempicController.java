package com.syes.syes_springboot.controller;

import com.syes.syes_springboot.mapper.ItempicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hasdsd
 * @since 2022-09-03
 */
@RestController
@RequestMapping("/itempic")
public class ItempicController {
    @Autowired
    ItempicMapper itempicMapper;


}
