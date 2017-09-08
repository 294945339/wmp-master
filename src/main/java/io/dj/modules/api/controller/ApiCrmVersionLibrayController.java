package io.dj.modules.api.controller;

import io.dj.common.annotation.ApiIPFilter;
import io.dj.common.utils.R;
import io.dj.modules.api.annotation.AuthIgnore;
import io.dj.modules.goods.domain.VersionLibraryDomain;
import io.dj.modules.goods.service.VersionLibraryService;
import io.dj.modules.job.bean.CrmVersionLibraryBean;
import io.dj.modules.job.service.CrmVersionLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/crm/cmVersionLibray")
public class ApiCrmVersionLibrayController {

    @Autowired private VersionLibraryService versionLibraryService;
    @Autowired private CrmVersionLibraryService crmVersionLibraryService;

    /**
     * 保存
     */
    @AuthIgnore
    @ApiIPFilter
    @GetMapping("save/{id}")
    public R save(@PathVariable("id") String id) {
        List<CrmVersionLibraryBean> beans = crmVersionLibraryService.getCrmVersionLibrary(id);
        if(beans.size() <= 0){
            return R.error("数据库找不到该条记录,id:"+id+"");
        }

        Map<String,Object> map = new HashMap<String, Object>(){{
            put("crmId",id);
        }};
        List<VersionLibraryDomain> lists = versionLibraryService.queryList(map);
        Boolean isEq = false;
        VersionLibraryDomain domain = new VersionLibraryDomain();

        if(lists.size() > 0){
            isEq = true;
            domain = lists.get(0);
        }
        crmVersionLibraryService.setSave(domain,beans.get(0),isEq);
        return R.ok("收到crm企业客户保存请求,id:"+id+"");
    }

    /**
     * 删除
     */
    @AuthIgnore
    @ApiIPFilter
    @GetMapping("delete/{id}")
    public R delete(@PathVariable("id") String id) {
        List<VersionLibraryDomain> domains = versionLibraryService.queryList(new HashMap<String, Object>() {{
            put("crmId",id);
        }
        });
        if(domains.size() > 0){
            versionLibraryService.delete(domains.get(0).getId());
        }
        System.out.println(id);
        return R.ok("收到crm企业客户类别删除请求,id为:"+id);
    }
}
