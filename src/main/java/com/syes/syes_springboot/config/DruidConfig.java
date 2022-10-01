//package com.syes.syes_springboot.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.support.http.StatViewServlet;
//import com.alibaba.druid.support.http.WebStatFilter;
//import com.alibaba.druid.wall.WallConfig;
//import com.alibaba.druid.wall.WallFilter;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import java.util.*;
//
//
//@Configuration
//public class DruidConfig {
//
//
//    @ConfigurationProperties(prefix = "spring.datasource")
//    @Bean
//    public DataSource druid() {
//
//        List filterList = new ArrayList<>();
//        filterList.add(wallFilter());
//        DruidDataSource druidDataSource = new DruidDataSource();
//        druidDataSource.setProxyFilters(filterList);
//        return druidDataSource;
//
////        return new DruidDataSource();
//    }
//
//    /**
//     * 配置druid执行多条sql（批量执行），避免报sql注入异常
//     * 链式配置
//     *
//     * @return
//     */
//    @Bean
//    public WallFilter wallFilter() {
//        WallFilter wallFilter = new WallFilter();
//        wallFilter.setConfig(wallConfig());
//        return wallFilter;
//    }
//
//
//    @Bean
//    public WallConfig wallConfig() {
//        WallConfig config = new WallConfig();
//        config.setMultiStatementAllow(true);//允许一次执行多条语句
//        config.setNoneBaseStatementAllow(true);//允许非基本语句的其他语句
//        return config;
//    }
//
//    //配置Druid监控
//    //1、配置一个管理后台的servlet
//    @Bean
//    public ServletRegistrationBean StatViewServlet() {
//        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
//        Map<String, String> initParams = new HashMap<>();
//        initParams.put("loginUsername", "root");
//        initParams.put("loginPassword", "123456");
//        initParams.put("allow", "");
//
//        //initParams.put("deny","localhost");
//        bean.setInitParameters(initParams);
//
//        //bean.addInitParameter("loginUsername","root");
//        //bean.addInitParameter("loginPassword","123456");
//        //bean.addInitParameter("allow","127.0.0.1");
//
//        return bean;
//    }
//
//    //2、配置一个监控的filter
//    @Bean
//    public FilterRegistrationBean webStatFilter() {
//        FilterRegistrationBean bean = new FilterRegistrationBean();
//        bean.setFilter(new WebStatFilter());
//
//        Map<String, String> initParams = new HashMap<>();
//        initParams.put("exclusions", "*.js ,*.css ,/druid/*");
//
//        bean.setInitParameters(initParams);
//
//        bean.setUrlPatterns(Arrays.asList("/*"));
//        return bean;
//    }
//
//}
