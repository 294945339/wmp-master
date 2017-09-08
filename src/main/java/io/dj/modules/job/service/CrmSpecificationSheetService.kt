package io.dj.modules.job.service

import io.dj.modules.goods.domain.SpecificationSheetDomain
import io.dj.modules.job.bean.CrmSpecificationSheetBean
import java.util.ArrayList


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/15
 * Time: 15:05
 * Email: 294945339@qq.com
 */

interface CrmSpecificationSheetService {

    fun updateCrmSpecificationSheet()
    fun updateAll()
    fun getCrmSpecificationSheet(id: String): ArrayList<CrmSpecificationSheetBean>
    fun setSave(domain: SpecificationSheetDomain, bean: CrmSpecificationSheetBean,isEq: Boolean)

}