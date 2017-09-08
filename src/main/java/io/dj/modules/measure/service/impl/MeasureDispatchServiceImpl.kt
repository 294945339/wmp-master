package io.dj.modules.measure.service.impl

import io.dj.common.base.service.impl.BaseServiceImpl
import io.dj.modules.measure.dao.MeasureDispatchDao
import io.dj.modules.measure.domain.MeasureDispatchDomain
import io.dj.modules.measure.service.MeasureDispatchService
import org.springframework.stereotype.Service


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/18
 * Time: 8:38
 * Email: 294945339@qq.com
 */
@Service("measureDispatchService")
open class MeasureDispatchServiceImpl: BaseServiceImpl<MeasureDispatchDao,MeasureDispatchDomain>(),MeasureDispatchService {
}