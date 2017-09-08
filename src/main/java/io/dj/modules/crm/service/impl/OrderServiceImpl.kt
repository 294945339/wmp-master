package io.dj.modules.crm.service.impl

import io.dj.common.base.service.impl.BaseServiceImpl
import io.dj.modules.crm.dao.OrderDao
import io.dj.modules.crm.domain.OrderDomain
import io.dj.modules.crm.service.OrderService
import org.springframework.stereotype.Service


/**
 * @author  dj
 * @email 294945339@qq.com
 * @create 2017-08-24 11:47
 * @desc      量体通知单servieImpl
 **/
@Service("orderService")
open class OrderServiceImpl: BaseServiceImpl<OrderDao,OrderDomain>(),OrderService {
}