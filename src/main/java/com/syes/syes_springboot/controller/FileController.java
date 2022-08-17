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

        // 获取需要上传数据库的属性
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        String size = String.valueOf(file.getSize());
        String url = downloadPath;
        String md5 = SecureUtil.md5(file);

        // 获取uuid
        String uuid = IdUtil.fastSimpleUUID();

        // 文件去重
        url += uuid + type;
        String oldUrl = md5Exists(md5);
        if (oldUrl != null) { // 文件重复
            url = oldUrl;
        } else {
            // 文件不重复，上传文件
            String path = uploadPath + uuid + type;
            uploadToServer(file, path);
        }

        // name是主键吧，为了不报错就加个判断吧
        if (nameExists(originalFilename)){
            return Result.success("名字已经存在");
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
        Map<String, Object> map = new HashMap<>();
        map.put("originalFilename", originalFilename);
        map.put("md5", md5);
        map.put("type", type);
        map.put("uuid", uuid);
        map.put("size", size);
        map.put("url", url);

        return Result.success(map);
    }

    //下载文件
//    @GetMapping("/{Fileuuid}")
//    public void download(@PathVariable("Fileuuid") String FileUUID, HttpServletResponse response) throws IOException {
//        //根据UUID获取文件
//        java.io.File file = new java.io.File(uploadPath + FileUUID);
//        //设置输出流的格式
//        ServletOutputStream os = response.getOutputStream();
//        //标准表头
//        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(FileUUID, "UTF-8"));
//        response.setContentType("application/octet-stream");
//
//        //读取文件的字节流
//        os.write(FileUtil.readBytes(file));
//        os.flush();
//        os.close();
//    }

    // 上传到服务器
    private boolean uploadToServer(MultipartFile file, String path) {
        if (!file.isEmpty()) {
            try {
                file.transferTo(new java.io.File(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
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

    /*
    //    //获取配置
    @Value("/$/{/files/./upload/./path}/") //加斜杠因为博客会认成代码块
    private String fileUploadPath;




    //标记修改删除和修改是否可用
    @PutMapping("/put")
    public Result MarkDeleted(@RequestBody File file) {
        fileMapper.updateById(file);
        return Result.success();
    }

    @GetMapping("/id/{id}")
    public Result getFileById(@PathVariable("id") int id) {
        File file = fileMapper.selectById(id);
        return Result.success(file);
    }

    //分页查询
    @GetMapping("/page/p")
    public Result getUserByPage(@RequestParam("PageNumber") int PageNumber, @RequestParam("PageSize") int PageSize) {
        // 设置开始页
        int PageStart = (PageNumber - 1) * PageSize;

        // 获取总数
        QueryWrapper wrapper = new QueryWrapper();
        int total = fileMapper.selectCount(wrapper);

        // 查询
        System.out.println("Pagestart:" + PageStart + "  PageSize" + PageSize);
        List<File> result = fileMapper.slectByPage(PageStart, PageSize);

        Map<String, Object> res = new HashMap<>();
        res.put("total", total);
        res.put("data", result);
        return Result.success(res);
    }

    // 删除文件
    @DeleteMapping("/{id}")
    public Result deleteFileById(@PathVariable("id") int id, HttpServletResponse response) {
        int i = fileMapper.deleteById(id);
        return Result.success(i);
    }

    //上传文件
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        //获取名称
        String originalFilename = file.getOriginalFilename();
        //获取类型
        String type = FileUtil.extName(originalFilename);
        //获取大小
        long size = file.getSize();
        //判断配置文件的目录是否存在
        java.io.File uploadParentFile = new java.io.File(fileUploadPath);

        if (!uploadParentFile.exists()) {
            uploadParentFile.mkdirs();
        }

        //定义文件唯一标识位
        String uuid = IdUtil.fastSimpleUUID();
        //UUID，这样方便后续下载
        String FileUUID = uuid + StrUtil.DOT + type;

        //文件路径  UUID+点+文件类型
        java.io.File uploadFile = new java.io.File(fileUploadPath + FileUUID);

        //下面一大片操作目的是：遇到重复文件时，数据库增加数据，服务器储存不变
        String md5;
        String url;
        file.transferTo(uploadFile);
        md5 = SecureUtil.md5(uploadFile);


        File dbFile = getFileByMd5(md5);
        if (dbFile != null) {
            url = dbFile.getUrl();
            uploadFile.delete();
        } else {
            url = "http://192.168.0.123:8080/file/" + FileUUID;
        }
        //给数据库添加记录
        File saveFile = new File();
        saveFile.setName(originalFilename);
        saveFile.setType(type);
        saveFile.setSize(String.valueOf(size)); // 参数类型不符，转一下
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
        fileMapper.insert(saveFile);
        //返回了文件下载链接
        return url;
    }

    //下载文件
    @GetMapping("/{Fileuuid}")
    public void download(@PathVariable("Fileuuid") String FileUUID, HttpServletResponse response) throws IOException {
        //根据UUID获取文件
        java.io.File file = new java.io.File(fileUploadPath + FileUUID);
        //设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        //标准表头
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(FileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");

        //读取文件的字节流
        os.write(FileUtil.readBytes(file));
        os.flush();
        os.close();
    }

    //通过MD5查询数据
    private File getFileByMd5(String md5) {
        QueryWrapper<File> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        List<File> files = fileMapper.selectList(queryWrapper);
        return files.size() == 0 ? null : files.get(0);
    }


     */
}
