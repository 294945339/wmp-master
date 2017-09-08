package io.dj.modules.measure.service.impl

import io.dj.common.base.service.impl.BaseServiceImpl
import io.dj.modules.crm.dao.OrderPeoplesDao
import io.dj.modules.crm.domain.OrderPeoplesDomain
import io.dj.modules.goods.service.GoodsTypeService
import io.dj.modules.measure.service.OrderPeoplesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * email 294945339@qq.com
 * Date: 2017/8/26
 * Description:
 **/
@Service("orderPeoplesService")
open class OrderPeoplesServiceImpl : BaseServiceImpl<OrderPeoplesDao, OrderPeoplesDomain>(), OrderPeoplesService {

    @Autowired private val goodsTypeService: GoodsTypeService? = null


    override fun save(domain: OrderPeoplesDomain) {
        val jackets = goodsTypeService!!.queryList(object : HashMap<String, String>() {
            init {
                put("crmName", "西服上衣")
            }
        } as Map<String, Any>?)
        val trousers = goodsTypeService.queryList(object : HashMap<String, String>() {
            init {
                put("crmName", "西裤")
            }
        } as Map<String, Any>?)
        val vest = goodsTypeService.queryList(object : HashMap<String, String>() {
            init {
                put("crmName", "马夹")
            }
        } as Map<String, Any>?)
        val shirt = goodsTypeService.queryList(object : HashMap<String, String>() {
            init {
                put("crmName", "长袖衬衫")
            }
        } as Map<String, Any>?)
        val skirt = goodsTypeService.queryList(object : HashMap<String, String>() {
            init {
                put("crmName", "裙子")
            }
        } as Map<String, Any>?)
        val overcoat = goodsTypeService.queryList(object : HashMap<String, String>() {
            init {
                put("crmName", "大衣")
            }
        } as Map<String, Any>?)
        domain.jacket = jackets[0]
        domain.trousers = trousers[0]
        domain.vest = vest[0]
        domain.shirt = shirt[0]
        domain.skirt = skirt[0]
        domain.overcoat = overcoat[0]
    }

}