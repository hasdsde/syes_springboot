package com.syes.syes_springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.syes.syes_springboot.mapper") // 扫描mapper文件夹
public class SyesSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SyesSpringbootApplication.class, args);
    }

}
