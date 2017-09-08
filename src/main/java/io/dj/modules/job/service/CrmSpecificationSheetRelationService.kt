package io.dj.modules.job.service

import io.dj.modules.goods.domain.SpecificationSheetRelationDomain
import io.dj.modules.job.bean.CrmSpecificationSheetRelationBean


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/17
 * Time: 13:31
 * Email: 294945339@qq.com
 */
interface CrmSpecificationSheetRelationService {
    fun getCrmSpecificationSheetRelation(id: String,fatherId:String): ArrayList<CrmSpecificationSheetRelationBean>
    fun getDomainByMap(domain: SpecificationSheetRelationDomain, bean: CrmSpecificationSheetRelationBean): SpecificationSheetRelationDomain
    fun updateCrmSpecificationSheetRelation()
    }