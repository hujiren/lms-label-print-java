package com.apl.lms.label.print.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author hjr start
 * @Classname LmsLabelPrintApplication
 * @Date 2020/12/28 14:36
 */
@SpringBootApplication(
        scanBasePackages = {
                "com.apl.lib", //APL基本工具类
//                "com.apl.tenant", //多租户
                "com.apl.db.adb", // adb数据库操作助手
                "com.apl.cache", // redis代理
                "com.apl.shardingjdbc",
                "com.apl.lms.net",
                "com.apl.sys.lib",
                "com.apl.lms.label.print"},
        exclude= {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient//可以被注册中心发现
@EnableSwagger2
@MapperScan(basePackages = "com.apl.lms.label.print.mapper", sqlSessionFactoryRef = "sqlSessionFactoryForShardingjdbc")
public class LmsLabelPrintApplication {

    public static void main(String[] args) {

        SpringApplication.run(LmsLabelPrintApplication.class, args);
    }
}
