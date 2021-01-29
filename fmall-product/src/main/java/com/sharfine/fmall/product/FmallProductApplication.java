package com.sharfine.fmall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author sharfine
 */
@MapperScan("com.sharfine.fmall.product.dao")
@EnableDiscoveryClient
@SpringBootApplication
public class FmallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(FmallProductApplication.class, args);
    }

}
