package io.dj.modules.measure.dao

import io.dj.modules.measure.domain.AuditingDomain
import io.dj.modules.sys.dao.BaseDao
import org.apache.ibatis.annotations.Mapper


/**
 * @author  dj
 * @email 294945339@qq.com
 * @create 2017-08-29 8:47
 * @desc
 **/

@Mapper
interface AuditingDao : BaseDao<AuditingDomain> {
}