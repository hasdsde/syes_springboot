package com.syes.syes_springboot.service.impl;

import com.syes.syes_springboot.entity.File;
import com.syes.syes_springboot.mapper.FileMapper;
import com.syes.syes_springboot.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author test
 * @since 2022-08-17
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {

}
