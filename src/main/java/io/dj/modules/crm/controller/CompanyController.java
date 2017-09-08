package io.dj.modules.crm.controller;

import io.dj.common.utils.PageUtils;
import io.dj.common.utils.Query;
import io.dj.common.utils.R;
import io.dj.modules.crm.domain.CompanyDomain;
import io.dj.modules.crm.service.CompanyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/15
 * Time: 14:40
 * Email: 294945339@qq.com
 */

@RestController
@RequestMapping("/crm/company")
public class CompanyController {

    @Autowired private CompanyService companyService;

    /**
     * 所有列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goods:goodsType:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<CompanyDomain> userList = companyService.queryList(query);
        int total = companyService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

}
