package io.dj.modules.measure.controller;

import io.dj.common.annotation.SysLog;
import io.dj.common.base.controller.BaseController;
import io.dj.common.utils.PageUtils;
import io.dj.common.utils.Query;
import io.dj.common.utils.R;
import io.dj.modules.measure.domain.AuditingDomain;
import io.dj.modules.measure.service.AuditingService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author dj
 * @email 294945339@qq.com
 * @create 2017-08-29 9:45
 * @desc 审单规则
 **/
@RestController
@RequestMapping("/measure/auditing")
public class AuditingController extends BaseController {

    @Autowired private AuditingService auditingService;

    /**
     * 所有列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("measure:auditing:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<AuditingDomain> userList = auditingService.queryList(query);
        int total = auditingService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 具体信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("measure:auditing:info")
    public R info(@PathVariable("id") Long id) {
        AuditingDomain domain = auditingService.queryObject(id);
        return R.ok().put("clothingDict", domain);
    }

    /**
     * 保存
     */
    @SysLog("保存审单规则")
    @RequestMapping("/save")
    @RequiresPermissions("measure:auditing:save")
    public R save(@RequestBody AuditingDomain domain) {
        auditingService.save(domain);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除审单规则")
    @RequestMapping("/delete")
    @RequiresPermissions("measure:auditing:delete")
    public R delete(@RequestBody Long[] ids) {
        for(Long id : ids){
            auditingService.delete(id);
        }
        return R.ok();
    }
}
