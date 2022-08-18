package com.syes.syes_springboot.Utils;

import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SecureUtil {

    // 废弃的，过时的，无法处理大文件的
    public static String md5(MultipartFile file){
        // 面向cv编程，哈哈我是就是个废物
        try {
            //获取文件的byte信息，文件大了会报错
            byte[] uploadBytes = file.getBytes();
            // 拿到一个MD5转换器
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(uploadBytes);
            //转换为16进制
            return new BigInteger(1, digest).toString(16);
        } catch (Exception ignored) {

        }
        return null;
    }

    public static String bigMD5(File file){
        // 大文件报错byte是int设置长度，int超范围，哈哈我又偷懒去cv的
        BigInteger bi = null;
        try {
            byte[] buffer = new byte[8192];
            int len = 0;
            MessageDigest md = MessageDigest.getInstance("MD5");
//            File f = new File(path);
            FileInputStream fis = new FileInputStream(file);
            while ((len = fis.read(buffer)) != -1) {
                md.update(buffer, 0, len);
            }
            fis.close();
            byte[] b = md.digest();
            bi = new BigInteger(1, b);
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        assert bi != null;
        return bi.toString(16);
    }
}
