package io.dj.modules.measure.controller;

import io.dj.common.utils.PageUtils;
import io.dj.common.utils.Query;
import io.dj.common.utils.R;
import io.dj.modules.measure.domain.MeasureDispatchDomain;
import io.dj.modules.measure.service.MeasureDispatchService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author dj
 * @email 294945339@qq.com
 * @create 2017-08-28 9:36
 * @desc 量体派遣单
 **/
@RestController
@RequestMapping("/measure/measureDispatch")
public class MeasureDispatchController {
    @Autowired private MeasureDispatchService measureDispatchService;


    @RequestMapping("/list")
    @RequiresPermissions("measure:measureDispatch:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<MeasureDispatchDomain> userList = measureDispatchService.queryList(query);
        int total = measureDispatchService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 具体信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("measure:measureDispatch:info")
    public R info(@PathVariable("id") Long id) {
        MeasureDispatchDomain domain = measureDispatchService.queryObject(id);
        return R.ok().put("measureDispatch", domain);
    }
}
