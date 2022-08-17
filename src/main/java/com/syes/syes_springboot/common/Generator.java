package com.syes.syes_springboot.common;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.sun.org.apache.bcel.internal.generic.StackConsumer;

import java.util.Collections;

public class Generator {
    public static void main(String[] args) {
        FastAutoGenerator.create(
                        "jdbc:mysql://hasdsd.cn:3307/syes",
                        "root",
                        "123456")
                .globalConfig(builder -> {
                    builder.author("test") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .enableSwagger() // 开启 swagger 模式
                            // 指定输出目录
                            //直接右键复制项目根目录的绝对路径
                            .outputDir("D:/工具/编程/idea/java工作区/syes_springboot/src/main/java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.syes.syes_springboot") // 设置父包名
                            // 设置mapperXml生成路径
                            //直接右键复制项目mapper文件夹的绝对路径
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:/工具/编程/idea/java工作区/syes_springboot/src/main/resources/mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("file")//设置表名
                            .mapperBuilder()
                            .enableMapperAnnotation() // 开启mapper注解
                            .entityBuilder()    //entity前置，才能用lombok
                            .enableLombok()  //使用lombok
                            .controllerBuilder()
                            .enableRestStyle(); //restMapping注解
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
