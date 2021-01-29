package com.sharfine.fmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sharfine.common.utils.PageUtils;
import com.sharfine.common.utils.Query;
import com.sharfine.fmall.product.dao.CategoryDao;
import com.sharfine.fmall.product.entity.CategoryEntity;
import com.sharfine.fmall.product.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

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

    public List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> list){

       return list.stream()
               .filter(c -> c.getParentCid().equals(root.getCatId()))
               .peek(c -> c.setChildren(getChildren(c, list)))
               .sorted(Comparator.comparingInt(CategoryEntity::getSort))
               .collect(Collectors.toList());

    }

}