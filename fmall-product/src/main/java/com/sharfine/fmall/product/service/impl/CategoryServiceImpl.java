package com.sharfine.fmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sharfine.common.utils.PageUtils;
import com.sharfine.common.utils.Query;
import com.sharfine.fmall.product.dao.CategoryBrandRelationDao;
import com.sharfine.fmall.product.dao.CategoryDao;
import com.sharfine.fmall.product.entity.CategoryEntity;
import com.sharfine.fmall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryBrandRelationDao categoryBrandRelationDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        List<CategoryEntity> list = baseMapper.selectList(null);

        return list.stream().filter(c -> c.getParentCid().equals(0L))
                .peek(c -> c.setChildren(getChildren(c, list)))
                .sorted(Comparator.comparingInt(CategoryEntity::getSort))
                .collect(Collectors.toList());
    }

    @Override
    public Long[] findCategoryPath(Long catelogId) {
        CategoryEntity categoryEntity = baseMapper.selectById(catelogId);
        ArrayList<Long> path = new ArrayList<>();
        path.add(categoryEntity.getCatId());
        while (categoryEntity.getParentCid() != 0) {
            path.add(categoryEntity.getParentCid());
            categoryEntity = baseMapper.selectById(categoryEntity.getParentCid());
        }
        Collections.reverse(path);
        return path.toArray(new Long[0]);
    }

    @Transactional
    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationDao.updateCategory(category.getCatId(), category.getName());
    }

    public List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> list) {

        return list.stream()
                .filter(c -> c.getParentCid().equals(root.getCatId()))
                .peek(c -> c.setChildren(getChildren(c, list)))
                .sorted(Comparator.comparingInt(CategoryEntity::getSort))
                .collect(Collectors.toList());

    }

}