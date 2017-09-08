package io.dj.modules.measure.service.impl

import io.dj.common.base.service.impl.BaseServiceImpl
import io.dj.modules.goods.domain.BodyValueDomain
import io.dj.modules.goods.service.BodyValueService
import io.dj.modules.measure.dao.ClothingSpecialBodyDao
import io.dj.modules.measure.domain.ClothingSpecialBodyDomain
import io.dj.modules.measure.service.ClothingSpecialBodyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * email 294945339@qq.com
 * Date: 2017/8/26
 * Description:
 **/
@Service("clothingSpecialBodyService")
open class ClothingSpecialBodyServiceImpl: BaseServiceImpl<ClothingSpecialBodyDao,ClothingSpecialBodyDomain>(),
        ClothingSpecialBodyService {

    @Autowired private val bodyValueService : BodyValueService? = null

    override fun save(domain: ClothingSpecialBodyDomain){
        if (domain.bodyValueIds.isNotEmpty()){
            val bodyVlaueIds = arrayOfNulls<Long>(domain.bodyValues.size)
            for(i in domain.bodyValues.indices){
                //保存后进行添加
                bodyValueService!!.save(domain.bodyValues[i])
                bodyVlaueIds[i] = domain.bodyValues[i].id
                domain.bodyValueIds = bodyVlaueIds
            }
        }
        super.save(domain)
    }

    override fun queryObject(id: Long): ClothingSpecialBodyDomain {
        return getObject(super.queryObject(id))
    }

    override fun queryObject(domain : ClothingSpecialBodyDomain): ClothingSpecialBodyDomain {
        return getObject(super.queryObject(domain.id))
    }

    override fun queryList(map: Map<String, Any>): List<ClothingSpecialBodyDomain> {
        val list = super.queryList(map)
        val admains = ArrayList<ClothingSpecialBodyDomain>()
        list.indices.mapTo(admains) { getObject(super.queryObject(list[it].id)) }
        return admains
    }

    private fun getObject(domain: ClothingSpecialBodyDomain): ClothingSpecialBodyDomain{
        val bodyValueIds = domain.bodyValueIds
        val bodyValues = ArrayList<BodyValueDomain>()
        if(bodyValueIds.isNotEmpty()){
            bodyValueIds.mapTo(bodyValues) { bodyValueService!!.queryObject(it) }
        }
        domain.bodyValues = bodyValues
        return  domain
    }
}