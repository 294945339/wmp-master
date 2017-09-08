package io.dj.modules.job.service

import io.dj.modules.job.bean.CrmMeasureDispatchBean
import io.dj.modules.measure.domain.MeasureDispatchDomain
import java.util.ArrayList


/**
 * @author  dj
 * @email 294945339@qq.com
 * @create 2017-08-25 15:02
 * @desc
 **/

interface CrmMeasureDispatchService {
    fun updateCrmMeasureDispatch()
    fun getCrmMeasureDispatch(id: String): ArrayList<CrmMeasureDispatchBean>
    fun setSave(domain: MeasureDispatchDomain, bean: CrmMeasureDispatchBean, isEq: Boolean)
}