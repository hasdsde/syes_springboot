package com.syes.syes_springboot.controller;

import com.syes.syes_springboot.Utils.FileUtil;
import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.File;
import com.syes.syes_springboot.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author test
 * @since 2022-08-17
 */
//FileController.java
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileMapper fileMapper;

    @Value("${my.file-config.uploadPath}")
    private String uploadPath;

    @Value("${my.file-config.downloadPath}")
    private String downloadPath;

    //上传图片并返回ID
    @GetMapping("/id")
    public Result getURLfromid(@RequestParam("itemid") int itemid) {
        List<File> list = fileMapper.selectimgByid(itemid);
        return Result.success(list);
    }

    //    软删除
    @GetMapping("/status")
    private Result upDeleById(@RequestParam("id") int id, @RequestParam("status") Boolean status) {
        File file = new File();
        file.setId(id);
        file.setIs_delete(status);
        fileMapper.updateById(file);
        return Result.success();
    }

    //创建图片
    @PostMapping("/upload")
    public Result upload(@RequestPart(value = "file") final MultipartFile file, @RequestParam("userid") String userid) {
        // 返回结果集
        Map<String, Object> map = new FileUtil(fileMapper, uploadPath, downloadPath).uploadF(file, userid);
        return Result.success(map);
    }
}
