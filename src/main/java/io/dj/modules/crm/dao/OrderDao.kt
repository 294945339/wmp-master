package io.dj.modules.crm.dao

import io.dj.modules.crm.domain.OrderDomain
import io.dj.modules.sys.dao.BaseDao
import org.apache.ibatis.annotations.Mapper


/**
 * @author  dj
 * @email 294945339@qq.com
 * @create 2017-08-24 11:45
 * @desc      量体通知单Dao
 **/
@Mapper
interface OrderDao:BaseDao<OrderDomain> {
}