package io.dj.modules.goods.service.impl

import io.dj.common.base.service.impl.BaseServiceImpl
import io.dj.modules.goods.dao.GoodsToDictDao
import io.dj.modules.goods.domain.ClothingDictDomain
import io.dj.modules.goods.domain.GoodsToDictDomain
import io.dj.modules.goods.service.ClothingDictService
import io.dj.modules.goods.service.GoodsToDictService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/16
 * Time: 14:33
 * Email: 294945339@qq.com
 * 做表关联查询需重写查询方法
 */

@Service("goodsToDictService")
open class GoodsToDictServiceImpl : BaseServiceImpl<GoodsToDictDao, GoodsToDictDomain>(), GoodsToDictService {

    @Autowired private val clothingDictService: ClothingDictService? = null

    override fun queryObject(id: Long): GoodsToDictDomain {
        return getObject(super.queryObject(id))
    }

    override fun queryObject(domain : GoodsToDictDomain): GoodsToDictDomain {
        return getObject(super.queryObject(domain.id))
    }

    override fun queryList(map: Map<String, Any>): List<GoodsToDictDomain> {
        val list = super.queryList(map)
        val admains = ArrayList<GoodsToDictDomain>()
        list.indices.mapTo(admains) { getObject(super.queryObject(list[it].id)) }
        return admains
    }

    private fun getObject(domain: GoodsToDictDomain): GoodsToDictDomain{
        val clothingDictIds = domain.clothingDictIds
        val clothingDicts = ArrayList<ClothingDictDomain>()
        if(clothingDictIds.isNotEmpty()){
            clothingDictIds.mapTo(clothingDicts) { clothingDictService!!.queryObject(it) }
        }
        domain.clothingDicts = clothingDicts
        return  domain
    }
}