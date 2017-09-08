package io.dj.modules.job.bean

import java.io.Serializable


/**
 * @author  dj
 * @email 294945339@qq.com
 * @create 2017-08-24 15:44
 * @desc
 **/

open class CrmCustomerBean : Serializable {

    var name: String? = null            //客户名称
    var sex: String? = null        //性别
    var company: String? = null           //所属企业
    var depName: String? = null         //部门
    var crmId: String? = null

}