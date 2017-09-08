package io.dj.modules.api.controller;

import io.dj.common.annotation.ApiIPFilter;
import io.dj.common.utils.R;
import io.dj.modules.api.annotation.AuthIgnore;
import io.dj.modules.goods.domain.GoodsTypeDomain;
import io.dj.modules.goods.service.GoodsTypeService;
import io.dj.common.base.controller.BaseController;
import io.dj.modules.job.bean.CrmGoodsTypeBean;
import io.dj.modules.job.service.CrmGoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/10
 **/
@RestController
@RequestMapping("/api/crm/goodsType")
public class ApiCrmGoodsTypeController extends BaseController {

    @Autowired
    private GoodsTypeService goodsTypeService;

    @Autowired
    private CrmGoodsTypeService crmGoodsTypeService;

    /**
     * 保存
     */
    @AuthIgnore
    @ApiIPFilter
    @GetMapping("save/{id}")
    public R save(@PathVariable("id") String id) {
        List<CrmGoodsTypeBean> beans = crmGoodsTypeService.getCrmGoodsType(id);
        if(beans.size() <= 0){
            return R.error("数据库找不到该条记录,id:"+id+"");
        }

        Map<String,Object> map = new HashMap<String, Object>(){{
            put("crmId",id);
            put("status","0");
        }};
        List<GoodsTypeDomain> lists = goodsTypeService.queryList(map);
        Boolean isEq = false;
        GoodsTypeDomain domain = new GoodsTypeDomain();

        if(lists.size() > 0){
            isEq = true;
            domain = lists.get(0);
        }
        crmGoodsTypeService.setSave(domain,beans.get(0),isEq);
        return R.ok("收到crm商品类别保存请求,id:"+id+"");
    }

    /**
     * 删除
     */
    @AuthIgnore
    @ApiIPFilter
    @GetMapping("delete/{id}")
    public R delete(@PathVariable("id") String id) {
        List<GoodsTypeDomain> domains = goodsTypeService.queryList(new HashMap<String, Object>() {{
            put("crmId",id);
        }
        });
        if(domains.size() > 0){
            goodsTypeService.delete(domains.get(0).getId());
        }
        System.out.println(id);
        return R.ok("收到crm商品类别删除请求,id为:"+id);
    }

}
