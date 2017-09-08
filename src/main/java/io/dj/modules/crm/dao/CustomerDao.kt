package io.dj.modules.crm.dao

import io.dj.modules.crm.domain.CustomerDomain
import io.dj.modules.sys.dao.BaseDao
import org.apache.ibatis.annotations.Mapper


/**
 * @author  dj
 * @email 294945339@qq.com
 * @create 2017-08-24 10:53
 * @desc      客户dao
 **/

@Mapper
interface CustomerDao : BaseDao<CustomerDomain> {
}