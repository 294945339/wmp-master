package io.dj.modules.measure.controller;

import io.dj.common.annotation.SysLog;
import io.dj.common.base.controller.BaseController;
import io.dj.common.utils.PageUtils;
import io.dj.common.utils.Query;
import io.dj.common.utils.R;
import io.dj.modules.measure.domain.ClothingSpecialBodyDomain;
import io.dj.modules.measure.service.ClothingSpecialBodyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * email 294945339@qq.com
 * Date: 2017/8/26
 * Description:
 **/

@RestController
@RequestMapping("/measure/clothingSpecialBody")
public class ClothingSpecialBodyController extends BaseController {

    private ClothingSpecialBodyService clothingSpecialBodyService;

    /**
     * 所有列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("measure:clothingHabit:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<ClothingSpecialBodyDomain> userList = clothingSpecialBodyService.queryList(query);
        int total = clothingSpecialBodyService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 具体信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("measure:clothingHabit:info")
    public R info(@PathVariable("id") Long id) {
        ClothingSpecialBodyDomain domain = clothingSpecialBodyService.queryObject(id);
        return R.ok().put("clothingDict", domain);
    }

    /**
     * 保存
     */
    @SysLog("保存特体")
    @RequestMapping("/save")
    @RequiresPermissions("measure:clothingHabit:save")
    public R save(@RequestBody ClothingSpecialBodyDomain domain) {
        clothingSpecialBodyService.save(domain);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除着装习惯")
    @RequestMapping("/delete")
    @RequiresPermissions("measure:clothingHabit:delete")
    public R delete(@RequestBody Long[] ids) {
        for(Long id : ids){
            clothingSpecialBodyService.delete(id);
        }
        return R.ok();
    }

}