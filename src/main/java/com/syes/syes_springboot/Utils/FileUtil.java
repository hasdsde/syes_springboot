package com.syes.syes_springboot.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUtil {

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
}
