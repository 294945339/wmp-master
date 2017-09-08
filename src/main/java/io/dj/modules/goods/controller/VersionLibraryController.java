package io.dj.modules.goods.controller;

import io.dj.common.base.controller.BaseController;
import io.dj.common.utils.PageUtils;
import io.dj.common.utils.Query;
import io.dj.common.utils.R;
import io.dj.modules.goods.domain.VersionLibraryDomain;
import io.dj.modules.goods.service.VersionLibraryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author dj
 * @email 294945339@qq.com
 * @create 2017-08-24 10:23
 * @desc 版型库控制层
 **/

@RestController
@RequestMapping("/goods/versionLibrary")
public class VersionLibraryController extends BaseController{

    @Autowired private VersionLibraryService versionLibraryService;

    /**
     * 所有列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goods:versionLibrary:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<VersionLibraryDomain> userList = versionLibraryService.queryList(query);
        int total = versionLibraryService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

}
