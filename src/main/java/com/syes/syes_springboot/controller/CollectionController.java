package com.syes.syes_springboot.controller;

import com.syes.syes_springboot.mapper.CollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author eula
 * @since 2022-08-26
 */
@RestController
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    CollectionMapper collectionMapper;

    @GetMapping("/")
    public void aaa(){
        collectionMapper.
    };
}
