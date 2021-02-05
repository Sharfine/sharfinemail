package com.sharfine.fmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sharfine.common.utils.PageUtils;
import com.sharfine.common.utils.Query;
import com.sharfine.fmall.product.dao.AttrAttrgroupRelationDao;
import com.sharfine.fmall.product.entity.AttrAttrgroupRelationEntity;
import com.sharfine.fmall.product.service.AttrAttrgroupRelationService;
import com.sharfine.fmall.product.vo.AttrGroupRelationVo;
import com.sharfine.fmall.product.vo.AttrRespVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("attrAttrgroupRelationService")
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationDao, AttrAttrgroupRelationEntity> implements AttrAttrgroupRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrAttrgroupRelationEntity> page = this.page(
                new Query<AttrAttrgroupRelationEntity>().getPage(params),
                new QueryWrapper<AttrAttrgroupRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void deleteRelation(AttrGroupRelationVo[] relationVos) {
        List<AttrAttrgroupRelationEntity> relationEntities = Arrays.stream(relationVos).map(relationVO -> {
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(relationVO, relationEntity);
            return relationEntity;
        }).collect(Collectors.toList());
        //delete from pms_attr_attrgroup_relation where (attr_id=? and attr_group_id=?) or (attr_id=? and attr_group_id=?)
        baseMapper.deleteBatchRelation(relationEntities);
    }

    @Override
    public void setRelation(List<AttrRespVo> attrRespVos) {

        List<AttrAttrgroupRelationEntity> collect = attrRespVos.stream().map(item -> {
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(item, relationEntity);
            return relationEntity;
        }).collect(Collectors.toList());
        this.saveBatch(collect);
    }

}