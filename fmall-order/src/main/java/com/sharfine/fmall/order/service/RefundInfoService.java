package com.sharfine.fmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sharfine.common.utils.PageUtils;
import com.sharfine.fmall.order.entity.RefundInfoEntity;

import java.util.Map;

/**
 * 退款信息
 *
 * @author sharfine
 * @date 2021-01-21 14:13:16
 */
public interface RefundInfoService extends IService<RefundInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

