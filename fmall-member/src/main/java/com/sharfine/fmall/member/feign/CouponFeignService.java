package com.sharfine.fmall.member.feign;

import com.sharfine.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: Sharfine
 * @createTime: 2021/1/22 11:46
 */
@FeignClient("fmall-coupon")
public interface CouponFeignService {

    /**
     * @return
     */
    @RequestMapping("/coupon/coupon/member/list")
    R membercoupons();

}
