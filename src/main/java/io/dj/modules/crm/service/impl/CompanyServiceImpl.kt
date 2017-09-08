package io.dj.modules.crm.service.impl

import io.dj.common.base.service.impl.BaseServiceImpl
import io.dj.modules.crm.dao.CompanyDao
import io.dj.modules.crm.domain.CompanyDomain
import io.dj.modules.crm.service.CompanyService
import org.springframework.stereotype.Service


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/15
 * Time: 11:29
 * Email: 294945339@qq.com
 */
@Service("companyService")
class CompanyServiceImpl:BaseServiceImpl<CompanyDao,CompanyDomain>(),CompanyService {
}