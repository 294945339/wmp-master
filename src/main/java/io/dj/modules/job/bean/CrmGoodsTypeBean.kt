package io.dj.modules.job.bean

import java.io.Serializable

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/12
 **/
open class CrmGoodsTypeBean : Serializable {
    
     var crmId: String? = null
     var code: String? = null
     var crmName: String? = null
     var unit: String? = null    //单位
     var typeStr: String? = null

     var erpId: String? = null
     var erpName: String? = null

}