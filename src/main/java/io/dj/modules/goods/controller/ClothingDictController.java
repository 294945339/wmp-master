package io.dj.modules.goods.controller;

import io.dj.common.annotation.SysLog;
import io.dj.common.utils.PageUtils;
import io.dj.common.utils.Query;
import io.dj.common.utils.R;
import io.dj.modules.goods.domain.ClothingDictDomain;
import io.dj.modules.goods.service.ClothingDictService;
import io.dj.common.base.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/7/29
 * 服饰字典
 **/
@RestController
@RequestMapping("/goods/clothingDict")
public class ClothingDictController extends BaseController {

    @Autowired
    private ClothingDictService clothingDictService;

    /**
     * 所有列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goods:clothingDict:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<ClothingDictDomain> userList = clothingDictService.queryList(query);
        int total = clothingDictService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 字典信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("goods:clothingDict:info")
    public R info(@PathVariable("id") Long id) {
        ClothingDictDomain domain = clothingDictService.queryObject(id);
        return R.ok().put("clothingDict", domain);
    }

    /**
     * 保存
     */
    @SysLog("保存服饰字典")
    @RequestMapping("/save")
    @RequiresPermissions("goods:clothingDict:save")
    public R save(@RequestBody ClothingDictDomain domain) {
        clothingDictService.save(domain);
        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改服饰字典")
    @RequestMapping("/update")
    @RequiresPermissions("goods:clothingDict:update")
    public R update(@RequestBody ClothingDictDomain domain) {
        clothingDictService.save(domain);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除服饰字典")
    @RequestMapping("/delete")
    @RequiresPermissions("goods:clothingDict:delete")
    public R delete(@RequestBody Long[] ids) {
        for(Long id : ids){
            clothingDictService.delete(id);
        }
        return R.ok();
    }

    @RequestMapping("/typeList")
    @RequiresPermissions("goods:clothingDict:list")
    public R getTypeList(){
        return R.ok().put("typeList", clothingDictService.getTypeList());
    }
}
