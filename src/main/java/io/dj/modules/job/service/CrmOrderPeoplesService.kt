package io.dj.modules.job.service

import io.dj.modules.crm.domain.OrderPeoplesDomain
import io.dj.modules.job.bean.CrmOrderPeoplesBean
import java.util.ArrayList

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * email 294945339@qq.com
 * Date: 2017/8/26
 * Description:
 **/

interface CrmOrderPeoplesService {
    fun getCrmOrderPeoples(id: String, fatherId: String): ArrayList<CrmOrderPeoplesBean>
    fun updateCrmOrderPeoples(fatherCode: String)
    fun setSave(domain: OrderPeoplesDomain, bean: CrmOrderPeoplesBean, isEq: Boolean)
    fun setSave(domain: OrderPeoplesDomain, bean: OrderPeoplesDomain, isEq: Boolean)
}