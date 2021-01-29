package com.sharfine.fmall.thirdparty;

import com.aliyun.oss.OSSClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class FmallThirdPartyApplicationTests {
    @Autowired
    OSSClient ossClient;

    @Test
    void test() {
        File file = new File("C:\\Users\\sharfine\\Desktop\\图片\\56eba65494eef01fe39bb7bdf7fe9925bd317da4.gif");
        ossClient.putObject("f-mall","56eba65494eefa01fe39bb7bdf7fe9925bd317da4.jpg", file);
        ossClient.shutdown();
    }
    @Test
    void contextLoads() {
    }

}
