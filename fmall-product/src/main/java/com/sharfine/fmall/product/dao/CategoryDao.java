package com.sharfine.fmall.product.dao;

import com.sharfine.fmall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author sharfine
 * @email sharfine@gmail.com
 * @date 2021-01-21 11:23:41
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
