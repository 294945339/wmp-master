package io.dj.modules.crm.service.impl

import io.dj.common.base.service.impl.BaseServiceImpl
import io.dj.modules.crm.dao.CustomerDao
import io.dj.modules.crm.domain.CustomerDomain
import io.dj.modules.crm.service.CustomerService


/**
 * @author  dj
 * @email 294945339@qq.com
 * @create 2017-08-24 11:19
 * @desc      顾客ServiceImpl
 **/

open class CustomerServiceImpl:BaseServiceImpl<CustomerDao,CustomerDomain>(),CustomerService {
}