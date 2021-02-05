package com.sharfine.fmall.product;

import com.sharfine.fmall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class FmallProductApplicationTests {
    @Autowired
    BrandService brandService;

    @Test
    void contextLoads() {
        BigDecimal a = new BigDecimal("1.00");
        System.out.println(a.intValue());
    }

}
