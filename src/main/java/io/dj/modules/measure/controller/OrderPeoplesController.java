package io.dj.modules.measure.controller;

import io.dj.common.annotation.SysLog;
import io.dj.common.base.controller.BaseController;
import io.dj.common.utils.PageUtils;
import io.dj.common.utils.Query;
import io.dj.common.utils.R;
import io.dj.modules.crm.domain.OrderPeoplesDomain;
import io.dj.modules.measure.service.OrderPeoplesService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * email 294945339@qq.com
 * Date: 2017/8/26
 * Description:量体人员名单
 **/

@RestController
@RequestMapping("/measure/orderPeoples")
public class OrderPeoplesController extends BaseController {

    @Autowired private OrderPeoplesService orderPeoplesService;

    /**
     * 所有列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("measure:orderPeoples:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<OrderPeoplesDomain> userList = orderPeoplesService.queryList(query);
        int total = orderPeoplesService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 具体信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("measure:orderPeoples:info")
    public R info(@PathVariable("id") Long id) {
        OrderPeoplesDomain domain = orderPeoplesService.queryObject(id);
        return R.ok().put("clothingDict", domain);
    }

    /**
     * 保存
     */
    @SysLog("保存量体人员名单")
    @RequestMapping("/save")
    @RequiresPermissions("measure:orderPeoples:save")
    public R save(@RequestBody OrderPeoplesDomain domain) {
        orderPeoplesService.save(domain);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除量体人员名单")
    @RequestMapping("/delete")
    @RequiresPermissions("measure:orderPeoples:delete")
    public R delete(@RequestBody Long[] ids) {
        for(Long id : ids){
            orderPeoplesService.delete(id);
        }
        return R.ok();
    }

}