package io.dj.modules.job.bean

import io.dj.common.base.enums.SexEnum
import io.dj.modules.goods.enums.SpecificationSheetTypeEnum
import java.io.Serializable


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/15
 * Time: 15:03
 * Email: 294945339@qq.com
 */

open class CrmSpecificationSheetBean : Serializable {

    var crmId: String? = null
    var code: String? = null            //规格表代码
    var name: String? = null           //规格表名称
    var sex = SexEnum.man            //男女
    var type: SpecificationSheetTypeEnum? = null            //类别
    var versionName: String? = null      //版本名称
    var canUse: Boolean? = true         //是否使用
    var fileCode: String? = null            //文件编码
    var specificationSheetRelationIds: Array<Long>? = null  //规格表子表

}