package com.sharfine.fmall.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("com.sharfine.fmall.coupon.dao")
@EnableDiscoveryClient
@SpringBootApplication
public class FmallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(FmallCouponApplication.class, args);
    }

}
