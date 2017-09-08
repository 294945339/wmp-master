package io.dj.modules.job.service

import io.dj.modules.crm.domain.OrderDomain
import io.dj.modules.job.bean.CrmOrderBean


/**
 * @author  dj
 * @email 294945339@qq.com
 * @create 2017-08-24 14:05
 * @desc   crm量体通知单service
 **/

interface CrmOrderService {
    fun updateCrmOrder()
    fun getCrmOrder(id: String): ArrayList<CrmOrderBean>
    fun setSave(domain: OrderDomain, bean: CrmOrderBean, isEq: Boolean)
}