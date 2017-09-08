package io.dj.modules.job.bean

import java.io.Serializable

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/4
 **/
open class DapOfficeBean : Serializable {

    var grade: String? = null
    //上级部门ID，一级部门为0
    var parentId: String? = null
    //部门名称
    var name: String? = null

    var id: String? = null

    var parentIds: String? = null

    var delFlag: String? = null
}