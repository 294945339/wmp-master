package io.dj.modules.measure.service.impl

import io.dj.common.base.service.impl.BaseServiceImpl
import io.dj.modules.measure.dao.AuditingDao
import io.dj.modules.measure.domain.AuditingDomain
import io.dj.modules.measure.service.AuditingService
import org.springframework.stereotype.Service


/**
 * @author  dj
 * @email 294945339@qq.com
 * @create 2017-08-29 9:43
 * @desc
 **/
@Service("auditingService")
open class AuditingServiceImpl : BaseServiceImpl<AuditingDao,AuditingDomain>(),AuditingService{
}