package com.syes.syes_springboot.Rcontroller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.syes.syes_springboot.Utils.FileUtil;
import com.syes.syes_springboot.common.Result;
import com.syes.syes_springboot.entity.File;
import com.syes.syes_springboot.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Rfile")
public class RFileController {
    @Resource
    private FileMapper fileMapper;

    @Value("${my.file-config.uploadPath}")
    private String uploadPath;

    @Value("${my.file-config.downloadPath}")
    private String downloadPath;

    //分页查询
    @GetMapping("/page")
    public Result slectByPage(@RequestParam("pagesize") int pagesize, @RequestParam("currentpage") int currentPage, @RequestParam("searchtext") String SearchText) {
        Integer total;
        int StartPage = (currentPage - 1) * pagesize; //开始页数
        QueryWrapper<File> wrapper = new QueryWrapper<>();
        wrapper.eq("userid", SearchText);
        List<File> fileList = new ArrayList<>();
        if (SearchText.isEmpty()) {
            fileList = fileMapper.slectByPage(StartPage, pagesize); //列表
            total = fileMapper.selectCount(null).intValue(); //获取总数
        } else {
            fileList = fileMapper.slectByPageSearch(StartPage, pagesize, SearchText); //列表
            total = fileMapper.selectCount(wrapper).intValue(); //获取总数
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", fileList);
        map.put("total", total);
        return Result.success(map);
    }

    //硬删除
    @DeleteMapping("/{id}")
    public Result DeleteByid(@PathVariable("id") int id) {
        fileMapper.deleteById(id);
        return Result.success();
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

    //上传图片并返回ID
    @GetMapping("/id")
    public Result getURLfromid(@RequestParam("itemid") int itemid) {
        List<File> list = fileMapper.selectimgByid(itemid);
        return Result.success(list);
    }


}
