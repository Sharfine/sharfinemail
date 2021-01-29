package com.sharfine.fmall.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("com.sharfine.fmall.ware.dao")
@EnableDiscoveryClient
@SpringBootApplication
public class FmallWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(FmallWareApplication.class, args);
    }

}
