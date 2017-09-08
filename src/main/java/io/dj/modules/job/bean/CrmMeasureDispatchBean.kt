package io.dj.modules.job.bean

import io.dj.common.base.enums.TaskStatusEnum
import io.dj.modules.crm.domain.OrderDomain
import java.io.Serializable


/**
 * @author  dj
 * @email 294945339@qq.com
 * @create 2017-08-25 14:53
 * @desc
 **/

open class CrmMeasureDispatchBean : Serializable {

    var code: String? = null          //派遣单号
    var companyCrmId: String? = null//公司
    var orderCrmCode: String? = null
    var type: String? = null    //量体方式
    var crmId: String? = null

    var taskStatus: TaskStatusEnum? = null//任务状态
    var taskTime: String? = null

    var order: OrderDomain? = null

}