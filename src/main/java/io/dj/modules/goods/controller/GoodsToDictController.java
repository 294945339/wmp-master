package io.dj.modules.goods.controller;

import io.dj.common.annotation.SysLog;
import io.dj.common.utils.PageUtils;
import io.dj.common.utils.Query;
import io.dj.common.utils.R;
import io.dj.modules.goods.domain.GoodsToDictDomain;
import io.dj.modules.goods.service.GoodsToDictService;
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
 * 商品类别部位配置(无参数)
 **/
@RestController
@RequestMapping("/goods/goodsToDict")
public class GoodsToDictController extends BaseController {

    @Autowired
    private GoodsToDictService goodsToDictService;

    /**
     * 所有列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goods:goodsToDict:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<GoodsToDictDomain> userList = goodsToDictService.queryList(query);
        int total = goodsToDictService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 商品类别部位信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("goods:goodsToDict:info")
    public R info(@PathVariable("id") Long id) {
        GoodsToDictDomain domain = goodsToDictService.queryObject(id);
        return R.ok().put("goodsToDict", domain);
    }

    /**
     * 保存
     */
    @SysLog("保存商品类别部位配置")
    @RequestMapping("/save")
    @RequiresPermissions("goods:goodsToDict:save")
    public R save(@RequestBody GoodsToDictDomain domain) {
        goodsToDictService.save(domain);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除商品类别部位配置")
    @RequestMapping("/delete")
    @RequiresPermissions("goods:goodsToDict:delete")
    public R delete(@RequestBody Long[] ids) {
        for(Long id : ids){
            goodsToDictService.delete(id);
        }
        return R.ok();
    }

}
