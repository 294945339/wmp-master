package io.dj.modules.job.bean

import java.io.Serializable


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/15
 * Time: 11:33
 * Email: 294945339@qq.com
 */

open class CrmCompanyBean: Serializable {

    var crmId: String? = null       //crmId
    var name: String? = null       //公司名称
    var code: String? = null        //公司编码
    var address: String? = null     //公司地址
    var phone: String? = null       //公司电话
    var salesmanName: String? = null        //业务员
    var salesmanPhone: String? = null       //业务员联系号码

}