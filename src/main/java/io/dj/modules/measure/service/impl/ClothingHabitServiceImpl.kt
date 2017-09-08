package io.dj.modules.measure.service.impl

import io.dj.common.base.service.impl.BaseServiceImpl
import io.dj.modules.goods.domain.BodyValueDomain
import io.dj.modules.goods.service.BodyValueService
import io.dj.modules.measure.dao.ClothingHabitDao
import io.dj.modules.measure.domain.ClothingHabitDomain
import io.dj.modules.measure.service.ClothingHabitService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


/**
 * @author  dj
 * @email 294945339@qq.com
 * @create 2017-08-25 10:39
 * @desc
 **/
@Service("clothingHabitService")
open class ClothingHabitServiceImpl:BaseServiceImpl<ClothingHabitDao,ClothingHabitDomain>(),ClothingHabitService {

    @Autowired private val bodyValueService : BodyValueService? = null

    override fun save(domain: ClothingHabitDomain){
        if (domain.bodyValues.isNotEmpty()){
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

    override fun queryObject(id: Long): ClothingHabitDomain {
        return getObject(super.queryObject(id))
    }

    override fun queryObject(domain : ClothingHabitDomain): ClothingHabitDomain {
        return getObject(super.queryObject(domain.id))
    }

    override fun queryList(map: Map<String, Any>): List<ClothingHabitDomain> {
        val list = super.queryList(map)
        val admains = ArrayList<ClothingHabitDomain>()
        list.indices.mapTo(admains) { getObject(super.queryObject(list[it].id)) }
        return admains
    }

    private fun getObject(domain: ClothingHabitDomain): ClothingHabitDomain{
        val bodyValueIds = domain.bodyValueIds
        val bodyValues = ArrayList<BodyValueDomain>()
        if(bodyValueIds.isNotEmpty()){
            bodyValueIds.mapTo(bodyValues) { bodyValueService!!.queryObject(it) }
        }
        domain.bodyValues = bodyValues
        return  domain
    }
}