package com.syes.syes_springboot.Utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.syes.syes_springboot.config.BusinessException;
import com.syes.syes_springboot.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtil {

//    @Resource
    private FileMapper fileMapper;

//    @Value("${my.file-config.uploadPath}")
    private String uploadPath;

//    @Value("${my.file-config.downloadPath}")
    private String downloadPath;

    public FileUtil(FileMapper fileMapper, String uploadPath, String downloadPath) {
        this.fileMapper = fileMapper;
        this.uploadPath = uploadPath;
        this.downloadPath = downloadPath;
    }

    public static String extName(String originalFilename) {
        if (originalFilename == null){
            return "";
        }
        if (originalFilename.contains(".")) {
            int index = originalFilename.indexOf('.');
            return originalFilename.substring(index);
        }
        return "";
    }

    public static byte[] readBytes(File file) throws IOException {
        byte[] bytes = new byte[(int) file.length()];
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(bytes);
        return bytes;
    }

    public Map<String, Object> uploadF(MultipartFile file, String userid) {
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
            throw new BusinessException("文件名重复");
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
        com.syes.syes_springboot.entity.File saveFile = new com.syes.syes_springboot.entity.File();
        saveFile.setName(originalFilename);
        saveFile.setType(type);
        saveFile.setSize(size);
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
        saveFile.setUserid(userid);
        saveFile.setCreatetime(LocalDateTime.now());
        fileMapper.insert(saveFile);

        // 测试
        map.put("originalFilename", originalFilename);
        map.put("type", type);
        map.put("size", size);
        map.put("url", url);
        map.put("md5", md5);
        map.put("userid", userid);

        map.put("uuid", uuid);

        QueryWrapper<com.syes.syes_springboot.entity.File> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", originalFilename);
        List<com.syes.syes_springboot.entity.File> files = fileMapper.selectList(queryWrapper);
        int resId = files.get(0).getId();
        map.put("id", resId);
        return map;
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
        QueryWrapper<com.syes.syes_springboot.entity.File> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        List<com.syes.syes_springboot.entity.File> files = fileMapper.selectList(queryWrapper);
        if (files.size() > 0) {
            return files.get(0).getUrl();
        } else {
            return null;
        }
    }

    // name是否存在
    private boolean nameExists(String name) {
        QueryWrapper<com.syes.syes_springboot.entity.File> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        List<com.syes.syes_springboot.entity.File> files = fileMapper.selectList(queryWrapper);
        return files.size() > 0;
    }
}
