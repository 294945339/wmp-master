package io.dj.modules.api.controller;

import io.dj.common.annotation.ApiIPFilter;
import io.dj.common.utils.R;
import io.dj.modules.api.annotation.AuthIgnore;
import io.dj.modules.goods.domain.SpecificationSheetDomain;
import io.dj.modules.goods.service.SpecificationSheetService;
import io.dj.modules.job.bean.CrmSpecificationSheetBean;
import io.dj.modules.job.service.CrmSpecificationSheetService;
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
 * email 294945339@qq.com
 * Date: 2017/8/23
 * Description: 规格表
 **/

@RestController
@RequestMapping("/api/crm/specificationSheet")
public class ApiCrmSpecificationSheetController {

    @Autowired private CrmSpecificationSheetService crmSpecificationSheetService = null;
    @Autowired private SpecificationSheetService specificationSheetService = null;

    /**
     * 保存
     */
    @AuthIgnore
    @ApiIPFilter
    @GetMapping("save/{id}")
    public R save(@PathVariable("id") String id) {
        List<CrmSpecificationSheetBean> beans = crmSpecificationSheetService.getCrmSpecificationSheet(id);
        if(beans.size() <= 0){
            return R.error("数据库找不到该条记录,id:"+id+"");
        }

        Map<String,Object> map = new HashMap<String, Object>(){{
            put("crmId",id);
        }};
        List<SpecificationSheetDomain> lists = specificationSheetService.queryList(map);
        Boolean isEq = false;
        SpecificationSheetDomain domain = new SpecificationSheetDomain();

        if(lists.size() > 0){
            isEq = true;
            domain = lists.get(0);
        }
        crmSpecificationSheetService.setSave(domain,beans.get(0),isEq);
        return R.ok("收到crm规格表保存请求,id:"+id+"");
    }

    /**
     * 删除
     */
    @AuthIgnore
    @ApiIPFilter
    @GetMapping("delete/{id}")
    public R delete(@PathVariable("id") String id) {
        List<SpecificationSheetDomain> domains = specificationSheetService.queryList(new HashMap<String, Object>() {{
            put("crmId",id);
        }
        });
        if(domains.size() > 0){
            specificationSheetService.delete(domains.get(0).getId());
        }
        System.out.println(id);
        return R.ok("收到crm规格表删除请求,id为:"+id);
    }

    @AuthIgnore
    @ApiIPFilter
    @GetMapping("get/{id}")
    public R get(@PathVariable("id") String id){
        SpecificationSheetDomain domain = specificationSheetService.queryObject(Long.valueOf(id));
        return R.ok("收到crm规格表删除请求,id为:"+id);
    }
}