package io.dj.modules.measure.controller;

import io.dj.common.annotation.SysLog;
import io.dj.common.utils.PageUtils;
import io.dj.common.utils.Query;
import io.dj.common.utils.R;
import io.dj.modules.measure.domain.FilingRuleDomain;
import io.dj.modules.measure.service.FilingRuleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author dj
 * @email 294945339@qq.com
 * @create 2017-08-28 16:53
 * @desc
 **/
@RestController
@RequestMapping("/measure/filingRule")
public class FilingRuleController {

    @Autowired private FilingRuleService filingRuleService;

    /**
     * 所有列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("measure:filingRule:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<FilingRuleDomain> userList = filingRuleService.queryList(query);
        int total = filingRuleService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 具体信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("measure:filingRule:info")
    public R info(@PathVariable("id") Long id) {
        FilingRuleDomain domain = filingRuleService.queryObject(id);
        return R.ok().put("clothingDict", domain);
    }

    /**
     * 保存
     */
    @SysLog("保存着装习惯")
    @RequestMapping("/save")
    @RequiresPermissions("measure:filingRule:save")
    public R save(@RequestBody FilingRuleDomain domain) {
        filingRuleService.save(domain);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除着装习惯")
    @RequestMapping("/delete")
    @RequiresPermissions("measure:filingRule:delete")
    public R delete(@RequestBody Long[] ids) {
        for(Long id : ids){
            filingRuleService.delete(id);
        }
        return R.ok();
    }
}
