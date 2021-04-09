package com.hyd.rbac;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 */
@SpringBootApplication
@ComponentScan("com.hyd")
@MapperScan("com.hyd.rbac.mapper")//mapper扫描
public class ServiceRBACApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceRBACApplication.class,args);
    }
}
