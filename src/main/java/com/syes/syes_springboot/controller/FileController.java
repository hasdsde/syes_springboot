package com.syes.syes_springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.syes.syes_springboot.Utils.FileUtil;
import com.syes.syes_springboot.Utils.IdUtil;
import com.syes.syes_springboot.Utils.SecureUtil;
import com.syes.syes_springboot.Utils.StrUtil;
import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.File;
import com.syes.syes_springboot.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
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


    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {

        // 测试用的map
        Map<String, Object> map = new HashMap<>();

        // 获取uuid
        String uuid = IdUtil.fastSimpleUUID();

        // 获取需要上传数据库的属性
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        String size = String.valueOf(file.getSize());
        String url = downloadPath;

        // name是主键吧，为了不报错就加个判断吧
        if (nameExists(originalFilename)) {
            return Result.success("名字已经存在");
        }

        // 上传文件，创建file方便删除
        String path = uploadPath + uuid + type;
        uploadToServer(file, path);
        java.io.File jFile = new java.io.File(path);

        // 追加md5
        String md5 = SecureUtil.bigMD5(jFile);

        // 文件去重
        url += uuid + type;
        String oldUrl = md5Exists(md5);
        if (oldUrl != null) { // 文件重复
            url = oldUrl;
            if (jFile.delete()) {
                map.put("file", "重复文件，删除了，用旧的");
            } else {
                map.put("file", "重复文件，没删了，出大事了");
            }
        }

        // 上传数据库
        File saveFile = new File();
        saveFile.setName(originalFilename);
        saveFile.setType(type);
        saveFile.setSize(size);
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
        fileMapper.insert(saveFile);

        // 测试
        map.put("originalFilename", originalFilename);
        map.put("type", type);
        map.put("size", size);
        map.put("url", url);
        map.put("md5", md5);

        map.put("uuid", uuid);

        return Result.success(map);
    }

    // 上传到服务器
    private void uploadToServer(MultipartFile file, String path) {
        if (!file.isEmpty()) {
            try {
                file.transferTo(new java.io.File(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 查询md5值
    private String md5Exists(String md5) {
        QueryWrapper<File> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        List<File> files = fileMapper.selectList(queryWrapper);
        if (files.size() > 0) {
            return files.get(0).getUrl();
        } else {
            return null;
        }
    }

    // name是否存在
    private boolean nameExists(String name) {
        QueryWrapper<File> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        List<File> files = fileMapper.selectList(queryWrapper);
        return files.size() > 0;
    }

}
