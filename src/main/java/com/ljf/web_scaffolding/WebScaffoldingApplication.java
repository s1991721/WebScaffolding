package com.ljf.web_scaffolding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by mr.lin on 2020/4/23
 */
@SpringBootApplication
@MapperScan(basePackages = "com.ljf.web_scaffolding.mapper")
public class WebScaffoldingApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebScaffoldingApplication.class, args);
    }

}
