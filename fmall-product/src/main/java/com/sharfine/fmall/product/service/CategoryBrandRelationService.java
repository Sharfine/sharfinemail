package com.sharfine.fmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sharfine.common.utils.PageUtils;
import com.sharfine.fmall.product.entity.BrandEntity;
import com.sharfine.fmall.product.entity.CategoryBrandRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author sharfine
 * @email sharfine@gmail.com
 * @date 2021-01-21 11:23:41
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveDetail(CategoryBrandRelationEntity categoryBrandRelation);

    void updatebrand(Long brandId, String name);

    List<BrandEntity> getBrandsByCatId(Long catId);
}

