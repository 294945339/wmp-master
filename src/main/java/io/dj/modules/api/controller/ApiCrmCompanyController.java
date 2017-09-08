package io.dj.modules.api.controller;

import io.dj.common.annotation.ApiIPFilter;
import io.dj.common.base.controller.BaseController;
import io.dj.common.utils.R;
import io.dj.modules.api.annotation.AuthIgnore;
import io.dj.modules.crm.domain.CompanyDomain;
import io.dj.modules.crm.service.CompanyService;
import io.dj.modules.goods.domain.GoodsTypeDomain;
import io.dj.modules.job.bean.CrmCompanyBean;
import io.dj.modules.job.service.CrmCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/15
 * Time: 13:49
 * Email: 294945339@qq.com
 */
@RestController
@RequestMapping("/api/crm/company")
public class ApiCrmCompanyController extends BaseController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private CrmCompanyService crmCompanyService;

    /**
     * 保存
     */
    @AuthIgnore
    @ApiIPFilter
    @GetMapping("save/{id}")
    public R save(@PathVariable("id") String id) {
        List<CrmCompanyBean> beans = crmCompanyService.getCrmCompany(id);
        if(beans.size() <= 0){
            return R.error("数据库找不到该条记录,id:"+id+"");
        }

        Map<String,Object> map = new HashMap<String, Object>(){{
            put("crmId",id);
            put("status","0");
        }};
        List<CompanyDomain> lists = companyService.queryList(map);
        Boolean isEq = false;
        CompanyDomain domain = new CompanyDomain();

        if(lists.size() > 0){
            isEq = true;
            domain = lists.get(0);
        }
        crmCompanyService.setSave(domain,beans.get(0),isEq);
        return R.ok("收到crm企业客户保存请求,id:"+id+"");
    }

    /**
     * 删除
     */
    @AuthIgnore
    @ApiIPFilter
    @GetMapping("delete/{id}")
    public R delete(@PathVariable("id") String id) {
        List<CompanyDomain> domains = companyService.queryList(new HashMap<String, Object>() {{
            put("crmId",id);
        }
        });
        if(domains.size() > 0){
            companyService.delete(domains.get(0).getId());
        }
        System.out.println(id);
        return R.ok("收到crm企业客户类别删除请求,id为:"+id);
    }
}
