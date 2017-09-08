package io.dj.modules.goods.service

import io.dj.common.base.bean.BaseBean
import io.dj.common.base.service.BaseService
import io.dj.modules.goods.domain.GoodsCatalogDomain

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * email 294945339@qq.com
 * Date: 2017/8/11
 * Description:
 **/

interface GoodsCatalogService: BaseService<GoodsCatalogDomain> {
    fun findNameList(): ArrayList<BaseBean>
}