package com.sharfine.fmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sharfine.common.utils.PageUtils;
import com.sharfine.common.utils.Query;
import com.sharfine.fmall.product.dao.CategoryBrandRelationDao;
import com.sharfine.fmall.product.entity.BrandEntity;
import com.sharfine.fmall.product.entity.CategoryBrandRelationEntity;
import com.sharfine.fmall.product.entity.CategoryEntity;
import com.sharfine.fmall.product.service.BrandService;
import com.sharfine.fmall.product.service.CategoryBrandRelationService;
import com.sharfine.fmall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryBrandRelationDao relationDao;

    @Autowired
    private CategoryService categoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveDetail(CategoryBrandRelationEntity categoryBrandRelation) {
        Long brandId = categoryBrandRelation.getBrandId();
        Long catelogId = categoryBrandRelation.getCatelogId();

        BrandEntity brandEntity = brandService.getById(brandId);
        String brandEntityName = brandEntity.getName();

        CategoryEntity categoryEntity = categoryService.getById(catelogId);
        String categoryEntityName = categoryEntity.getName();

        categoryBrandRelation.setBrandName(brandEntityName);
        categoryBrandRelation.setCatelogName(categoryEntityName);
//        this.save(categoryBrandRelation);
        baseMapper.insert(categoryBrandRelation);
    }

    @Override
    public void updatebrand(Long brandId, String name) {
        CategoryBrandRelationEntity categoryBrandRelationEntity = new CategoryBrandRelationEntity();
        categoryBrandRelationEntity.setId(brandId);
        categoryBrandRelationEntity.setBrandName(name);
        this.update(categoryBrandRelationEntity, new QueryWrapper<CategoryBrandRelationEntity>().eq("brand_id",
                brandId));
        //其他更新关联
    }

    @Override
    public List<BrandEntity> getBrandsByCatId(Long catId) {

        List<CategoryBrandRelationEntity> catelogId = relationDao
                .selectList(
                        new QueryWrapper<CategoryBrandRelationEntity>().eq("catelog_id", catId)
                );
        return catelogId.stream().map(item -> {
            Long brandId = item.getBrandId();
            return brandService.getById(brandId);
        }).collect(Collectors.toList());
    }
}