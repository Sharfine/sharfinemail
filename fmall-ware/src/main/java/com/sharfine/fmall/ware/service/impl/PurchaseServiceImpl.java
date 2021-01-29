package com.sharfine.fmall.ware.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sharfine.common.utils.PageUtils;
import com.sharfine.common.utils.Query;

import com.sharfine.fmall.ware.dao.PurchaseDao;
import com.sharfine.fmall.ware.entity.PurchaseEntity;
import com.sharfine.fmall.ware.service.PurchaseService;

/**
 * @author sharfine
 * @date 2021-01-21 14:26:03
 */

@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(params),
                new QueryWrapper<PurchaseEntity>()
        );

        return new PageUtils(page);
    }

}