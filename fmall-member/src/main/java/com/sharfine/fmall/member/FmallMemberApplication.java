package com.sharfine.fmall.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.sharfine.fmall.member.dao")
@EnableFeignClients("com.sharfine.fmall.member.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class FmallMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(FmallMemberApplication.class, args);
    }

}
