package io.dj.modules.job.bean

import java.io.Serializable

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/4
 **/
open class DapUserBean: Serializable {
    var name: String? = null
    var id: String? = null
    var loginName: String? = null
    var no: String? = null
    var password: String? = null
    var hold: Boolean? = false
    var deptId: String? = null
    var delFlag: String? = null
    var mobile:String? = ""
}