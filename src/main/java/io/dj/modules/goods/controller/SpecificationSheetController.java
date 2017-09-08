package io.dj.modules.goods.controller;

import io.dj.common.utils.PageUtils;
import io.dj.common.utils.Query;
import io.dj.common.utils.R;
import io.dj.modules.goods.domain.SpecificationSheetDomain;
import io.dj.modules.goods.service.SpecificationSheetService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/goods/specificationSheet")
public class SpecificationSheetController {

    @Autowired private SpecificationSheetService specificationSheetService;

    /**
     * 所有列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goods:specificationSheet:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        String searchAll = "";
        Boolean base = true;
        if(null != params.get("searchAll") && !"".equals(params.get("searchAll"))){
            searchAll = params.get("searchAll").toString();
            if("false".equals(searchAll)){
                base = false;
            }
        }
        List<SpecificationSheetDomain> list = specificationSheetService.queryList(query,base);
        int total = specificationSheetService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 规格表信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("goods:specificationSheet:info")
    public R info(@PathVariable("id") Long id) {
        SpecificationSheetDomain domain = specificationSheetService.queryObject(id);
        return R.ok().put("specificationSheet", domain);
    }


}
