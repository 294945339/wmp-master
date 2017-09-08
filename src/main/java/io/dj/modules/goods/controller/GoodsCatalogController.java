package io.dj.modules.goods.controller;

import io.dj.common.base.controller.BaseController;
import io.dj.common.utils.PageUtils;
import io.dj.common.utils.Query;
import io.dj.common.utils.R;
import io.dj.modules.goods.domain.GoodsCatalogDomain;
import io.dj.modules.goods.service.GoodsCatalogService;
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
 * email 294945339@qq.com
 * Date: 2017/8/11
 * Description:erp商品目录
 **/
@RestController
@RequestMapping("/goods/goodsCatalog")
public class GoodsCatalogController extends BaseController {

    @Autowired private GoodsCatalogService goodsCatalogService;

    /**
     * 所有列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goods:goodsCatalog:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<GoodsCatalogDomain> userList = goodsCatalogService.queryList(query);
        int total = goodsCatalogService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 所有列表
     */
    @RequestMapping("/nameList")
    @RequiresPermissions("goods:goodsCatalog:list")
    public R getNameList(){
        return R.ok().put("typeList", goodsCatalogService.findNameList());
    }

}