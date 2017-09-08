package io.dj.modules.crm.dao

import io.dj.modules.crm.domain.OrderPeoplesDomain
import io.dj.modules.sys.dao.BaseDao
import org.apache.ibatis.annotations.Mapper


/**
 * @author  dj
 * @email 294945339@qq.com
 * @create 2017-08-24 16:52
 * @desc
 **/
@Mapper
interface OrderPeoplesDao:BaseDao<OrderPeoplesDomain> {
}