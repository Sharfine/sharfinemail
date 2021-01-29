package com.sharfine.fmall.order.dao;

import com.sharfine.fmall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author sharfine
 * @email 
 * @date 2021-01-21 14:13:16
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
