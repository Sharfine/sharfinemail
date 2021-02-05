package com.sharfine.fmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sharfine.common.utils.PageUtils;
import com.sharfine.fmall.product.entity.AttrEntity;
import com.sharfine.fmall.product.vo.AttrRespVo;
import com.sharfine.fmall.product.vo.AttrVo;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author sharfine
 * @email sharfine@gmail.com
 * @date 2021-01-21 11:23:41
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String type);

    void updateAttr(AttrVo attr);

    AttrRespVo getAttrInfo(Long attrId);

    void saveAttr(AttrVo attr);

    PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId);

    List<AttrEntity> getRelationAttr(Long attrGroupId);
}

