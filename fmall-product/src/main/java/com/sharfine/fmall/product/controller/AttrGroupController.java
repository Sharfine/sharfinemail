package com.sharfine.fmall.product.controller;

import com.sharfine.common.utils.PageUtils;
import com.sharfine.common.utils.R;
import com.sharfine.fmall.product.entity.AttrEntity;
import com.sharfine.fmall.product.entity.AttrGroupEntity;
import com.sharfine.fmall.product.service.AttrAttrgroupRelationService;
import com.sharfine.fmall.product.service.AttrGroupService;
import com.sharfine.fmall.product.service.AttrService;
import com.sharfine.fmall.product.service.CategoryService;
import com.sharfine.fmall.product.vo.AttrGroupRelationVo;
import com.sharfine.fmall.product.vo.AttrGroupWithAttrsVo;
import com.sharfine.fmall.product.vo.AttrRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;



/**
 * 属性分组
 *
 * @author sharfine
 * @email sharfine@gmail.com
 * @date 2021-01-21 11:23:41
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    AttrAttrgroupRelationService relationService;
    @Autowired
    AttrService attrService;
    @Autowired
    private CategoryService categoryService;

    //product/attrgroup/{attrgroupId}/attr/relation
    @GetMapping("{attrgroupId}/attr/relation")
    public R getAttrsInfo(@PathVariable("attrgroupId") int attrgroupId) {
        List<AttrEntity> entities = attrGroupService.getAttrsInfo(attrgroupId);
        return R.ok().put("data", entities);
    }

    @PostMapping("/attr/relation")
    public R setRelation(@RequestBody List<AttrRespVo> attrRespVo) {
        relationService.setRelation(attrRespVo);
        return R.ok();
    }

    /**
     * 根据分类查询分组和属性信息
     *
     * @param catelogId catelogId
     * @return
     */
    ///product/attrgroup/{catelogId}/withattr
    @GetMapping("/{catelogId}/withattr")
    public R getAttrGroupWithAttrs(@PathVariable("catelogId") Long catelogId) {

        //1、查出当前分类下的所有属性分组，
        //2、查出每个属性分组的所有属性
        List<AttrGroupWithAttrsVo> vos = attrGroupService.getAttrGroupWithAttrsByCatelogId(catelogId);
        return R.ok().put("data", vos);
    }

    ///product/attrgroup/{attrgroupId}/noattr/relation
    @GetMapping("/{attrgroupId}/noattr/relation")
    public R attrNoRelation(@PathVariable("attrgroupId") Long attrgroupId,
                            @RequestParam Map<String, Object> params) {
        PageUtils page = attrService.getNoRelationAttr(params, attrgroupId);
        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")
    //@RequiresPermissions("product:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params, @PathVariable Long catelogId) {
        PageUtils page = attrGroupService.queryPage(params, catelogId);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    //@RequiresPermissions("product:attrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId) {
        AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        Long catelogId = attrGroup.getCatelogId();

        Long[] path = categoryService.findCategoryPath(catelogId);

        attrGroup.setCatelogPath(path);
        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
   // @RequiresPermissions("product:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds) {
        attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

    @PostMapping("attr/relation/delete")
    public R deleteRelation(@RequestBody AttrGroupRelationVo[] relationVos) {
        relationService.deleteRelation(relationVos);
        return R.ok();
    }

}
