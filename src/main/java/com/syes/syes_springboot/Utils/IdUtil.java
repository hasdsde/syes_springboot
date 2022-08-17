package com.syes.syes_springboot.Utils;

import java.util.Random;

public class IdUtil {

    public static String fastSimpleUUID() {
        // 时间加伪随机数
        String time = String.valueOf(System.currentTimeMillis());
        Random rand = new Random();
        String randStr = String.valueOf(rand.nextInt((int) (1e6)));
        // 转36进制
        long num = Long.parseLong(time + randStr);
        return Long.toString(num,36);
    }
}
