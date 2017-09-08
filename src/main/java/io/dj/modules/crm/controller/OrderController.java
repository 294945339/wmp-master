package io.dj.modules.crm.controller;

import io.dj.common.base.controller.BaseController;
import io.dj.common.utils.PageUtils;
import io.dj.common.utils.Query;
import io.dj.common.utils.R;
import io.dj.modules.crm.domain.OrderDomain;
import io.dj.modules.crm.service.OrderService;
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
 * @create 2017-08-28 9:17
 * @desc 量体通知单控制层
 **/

@RestController
@RequestMapping("/crm/order")
public class OrderController extends BaseController {

    @Autowired private OrderService orderService;

    /**
     * 所有列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("crm:order:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<OrderDomain> userList = orderService.queryList(query);
        int total = orderService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 量体通知单信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("crm:order:info")
    public R info(@PathVariable("id") Long id) {
        OrderDomain domain = orderService.queryObject(id);
        return R.ok().put("order", domain);
    }
}
