package io.dj.modules.job.bean

import io.dj.common.base.enums.SexEnum
import io.dj.common.base.enums.TaskStatusEnum

/**
 * @author  dj
 * @email 294945339@qq.com
 * @create 2017-08-24 13:58
 * @desc      crm量体通知单
 **/

open class CrmOrderPeoplesBean {

    var name: String? = null
    var dep: String? = null
    var code: String? = null           //工号
    var sex: SexEnum? = null
    var crmId: String? = null
    var fatherCode: String? = null
    var jacketId: String? = null     //上衣
    var jacketValue: String? = null
    var trousersId: String? = null   //裤子
    var trousersValue: String? = null
    var vestId: String? = null       //马甲
    var vestValue: String? = null
    var shirtId: String? = null     //衬衫
    var shirtValue: String? = null
    var skirtId: String? = null     //裙子
    var skirtValue: String? = null
    var overcoatId: String? = null     //大衣
    var overcoatValue: String? = null
    var taskStatus: TaskStatusEnum? = null

}