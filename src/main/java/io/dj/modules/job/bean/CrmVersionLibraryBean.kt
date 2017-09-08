package io.dj.modules.job.bean

import io.dj.modules.goods.domain.SpecificationSheetDomain
import java.io.Serializable


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/17
 * Time: 16:58
 * Email: 294945339@qq.com
 */

open class CrmVersionLibraryBean : Serializable {
    var specificationSheet: SpecificationSheetDomain? = null    //规格表
    var specificationSheetCode: String? = null    //规格表
    var code: String? = null                //版型编码
    var oldCode: String? = null             //原版型编号
    var type: String? = null
    var crmId: String? = null
    var fatherId:String? = null
}