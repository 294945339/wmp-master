package io.dj.modules.measure.service.impl

import io.dj.common.base.service.impl.BaseServiceImpl
import io.dj.modules.measure.dao.FilingRuleDao
import io.dj.modules.measure.domain.FilingRuleDomain
import io.dj.modules.measure.service.FilingRuleService
import org.springframework.stereotype.Service


/**
 * @author  dj
 * @email 294945339@qq.com
 * @create 2017-08-28 11:49
 * @desc
 **/

@Service("filingRuleService")
open class FilingRuleServiceImpl:BaseServiceImpl<FilingRuleDao,FilingRuleDomain>(),FilingRuleService  {
}