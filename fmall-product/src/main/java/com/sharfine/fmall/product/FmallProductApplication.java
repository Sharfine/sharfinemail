package com.sharfine.fmall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author sharfine
 */
@MapperScan("com.sharfine.fmall.product.dao")
@EnableDiscoveryClient
@EnableTransactionManagement
@SpringBootApplication
public class FmallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(FmallProductApplication.class, args);
    }

}
