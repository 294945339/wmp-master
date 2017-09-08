package io.dj.modules.goods.service

import io.dj.common.base.service.BaseService
import io.dj.modules.goods.domain.SpecificationSheetDomain


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/15
 * Time: 14:54
 * Email: 294945339@qq.com
 */

interface SpecificationSheetService: BaseService<SpecificationSheetDomain> {
    fun queryList(map: Map<String, Any>, searchAll: Boolean): List<SpecificationSheetDomain>
}