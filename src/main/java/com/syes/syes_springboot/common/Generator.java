package com.syes.syes_springboot.common;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class Generator {
    public static void main(String[] args) {
        FastAutoGenerator.create(
                        "jdbc:mysql://hasdsd.cn:3307/syes",
                        "root",
                        "123456")
                .globalConfig(builder -> {
                    builder.author("hasd") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .enableSwagger() // 开启 swagger 模式
                            // 指定输出目录
                            //直接右键复制项目根目录的绝对路径
                            .outputDir("D:/CODE/syes_springboot/src/main/java/");
                })
                .packageConfig(builder -> {
                    builder.parent("com.syes.syes_springboot") // 设置父包名
                            // 设置mapperXml生成路径
                            //直接右键复制项目mapper文件夹的绝对路径
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:/CODE/syes_springboot/src/main/resources/mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user")//设置表名
                            .entityBuilder()    //entity前置，才能用lombok
                            .enableLombok()  //使用lombok
                            .controllerBuilder()
                            .enableRestStyle(); //restMapping注解
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
