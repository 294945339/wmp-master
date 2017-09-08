package io.dj.modules.goods.service.impl

import io.dj.common.base.service.impl.BaseServiceImpl
import io.dj.modules.goods.dao.SpecificationSheetRelationDao
import io.dj.modules.goods.domain.BodyValueDomain
import io.dj.modules.goods.domain.SpecificationSheetRelationDomain
import io.dj.modules.goods.service.BodyValueService
import io.dj.modules.goods.service.SpecificationSheetRelationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/17
 * Time: 11:28
 * Email: 294945339@qq.com
 */
@Service("specificationSheetRelationService")
open class SpecificationSheetRelationServiceImpl :
        BaseServiceImpl<SpecificationSheetRelationDao, SpecificationSheetRelationDomain>(),
        SpecificationSheetRelationService {

    @Autowired private val bodyValueService : BodyValueService? = null

    override fun queryObject(id: Long): SpecificationSheetRelationDomain {
        return getObject(super.queryObject(id))
    }

    override fun queryObject(domain: SpecificationSheetRelationDomain): SpecificationSheetRelationDomain {
        return getObject(super.queryObject(domain.id))
    }

    override fun queryList(map: Map<String, Any>): List<SpecificationSheetRelationDomain> {
        val list = super.queryList(map)
        for(i in list.indices){
            list[i]= getObject(super.queryObject(list[i].id))
        }
        return list
    }

    private fun getObject(domain: SpecificationSheetRelationDomain): SpecificationSheetRelationDomain {
        val bodyValueIds = domain.bodyValueIds
        val bodyValues = ArrayList<BodyValueDomain>()
        bodyValueIds
                .map { bodyValueService!!.queryObject(it) }
                .filterTo(bodyValues) { null != it }
        domain.bodyValues = bodyValues
        return domain
    }
}