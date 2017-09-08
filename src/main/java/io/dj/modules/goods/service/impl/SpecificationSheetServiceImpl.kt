package io.dj.modules.goods.service.impl

import io.dj.common.base.service.impl.BaseServiceImpl
import io.dj.modules.goods.dao.SpecificationSheetDao
import io.dj.modules.goods.domain.SpecificationSheetDomain
import io.dj.modules.goods.service.SpecificationSheetRelationService
import io.dj.modules.goods.service.SpecificationSheetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/15
 * Time: 14:55
 * Email: 294945339@qq.com
 */
@Service("specificationSheetService")
open class SpecificationSheetServiceImpl : BaseServiceImpl<SpecificationSheetDao, SpecificationSheetDomain>(), SpecificationSheetService {

    @Autowired private val specificationSheetRelationService: SpecificationSheetRelationService? = null

    override fun queryObject(id: Long): SpecificationSheetDomain {
        return getObject(super.queryObject(id))
    }

    override fun queryObject(domain: SpecificationSheetDomain): SpecificationSheetDomain {
        return getObject(super.queryObject(domain.id))
    }

    override fun queryList(map: Map<String, Any>): List<SpecificationSheetDomain> {
        val list = super.queryList(map)
        for(i in list.indices){
            list[i]= getObject(super.queryObject(list[i].id))
        }
        return list
    }

    /**
     * false,不查询子关联表
     */
    override fun queryList(map: Map<String, Any>,searchAll: Boolean): List<SpecificationSheetDomain> {
        val list = super.queryList(map)
        if(searchAll){
            for(i in list.indices){
                list[i]= getObject(super.queryObject(list[i].id))
            }
        }
        return list
    }

    private fun getObject(domain: SpecificationSheetDomain): SpecificationSheetDomain {
        val map = HashMap<String,Long>()
        map.put("specificationSheet",domain.id)
        domain.specificationSheetRelations = specificationSheetRelationService!!.queryList(map as Map<String, Any>?)
        return domain
    }

}