package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//为了myBatis而配置的
@MapperScan("com.mapper")
public class MyAppAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyAppAdminApplication.class, args);
    }
}
