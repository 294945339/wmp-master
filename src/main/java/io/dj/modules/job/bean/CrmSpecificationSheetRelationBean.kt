package io.dj.modules.job.bean

import java.io.Serializable


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/17
 * Time: 13:36
 * Email: 294945339@qq.com
 */

open class CrmSpecificationSheetRelationBean : Serializable {

    var crmId: String? = null
    var code: String? = null           //规格表代码
    var type: String? = null            //型
    var size: String? = null            //规格
    var sort: String? = null           //排序
    var fartherId:String? = null

    var qyc: String? = null
    var hyc: String? = null
    var xw: String? = null
    var zy: String? = null
    var xb: String? = null
    var jk: String? = null
    var xc: String? = null
    var xf: String? = null
    var xk: String? = null
    var yw: String? = null
    var tw: String? = null
    var kc: String? = null
    var xfb: String? = null
    var hd: String? = null
    var tw2: String? = null
    var ql: String? = null
    var hl: String? = null
    var cdc: String? = null
    var hdc: String? = null
    var zd: String? = null
    var zhd: String? = null
    var qc: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CrmSpecificationSheetRelationBean) return false

        if (code != other.code) return false
        if (type != other.type) return false
        if (size != other.size) return false
        if (sort != other.sort) return false
        if (crmId != other.crmId) return false
        if (fartherId != other.fartherId) return false
        if (qyc != other.qyc) return false
        if (hyc != other.hyc) return false
        if (xw != other.xw) return false
        if (zy != other.zy) return false
        if (xb != other.xb) return false
        if (jk != other.jk) return false
        if (xc != other.xc) return false
        if (xf != other.xf) return false
        if (xk != other.xk) return false
        if (yw != other.yw) return false
        if (tw != other.tw) return false
        if (kc != other.kc) return false
        if (xfb != other.xfb) return false
        if (hd != other.hd) return false
        if (tw2 != other.tw2) return false
        if (ql != other.ql) return false
        if (hl != other.hl) return false
        if (cdc != other.cdc) return false
        if (hdc != other.hdc) return false
        if (zd != other.zd) return false
        if (zhd != other.zhd) return false
        if (qc != other.qc) return false

        return true
    }

    override fun hashCode(): Int {
        var result = code?.hashCode() ?: 0
        result = 31 * result + (type?.hashCode() ?: 0)
        result = 31 * result + (size?.hashCode() ?: 0)
        result = 31 * result + (sort?.hashCode() ?: 0)
        result = 31 * result + (crmId?.hashCode() ?: 0)
        result = 31 * result + (fartherId?.hashCode() ?: 0)
        result = 31 * result + (qyc?.hashCode() ?: 0)
        result = 31 * result + (hyc?.hashCode() ?: 0)
        result = 31 * result + (xw?.hashCode() ?: 0)
        result = 31 * result + (zy?.hashCode() ?: 0)
        result = 31 * result + (xb?.hashCode() ?: 0)
        result = 31 * result + (jk?.hashCode() ?: 0)
        result = 31 * result + (xc?.hashCode() ?: 0)
        result = 31 * result + (xf?.hashCode() ?: 0)
        result = 31 * result + (xk?.hashCode() ?: 0)
        result = 31 * result + (yw?.hashCode() ?: 0)
        result = 31 * result + (tw?.hashCode() ?: 0)
        result = 31 * result + (kc?.hashCode() ?: 0)
        result = 31 * result + (xfb?.hashCode() ?: 0)
        result = 31 * result + (hd?.hashCode() ?: 0)
        result = 31 * result + (tw2?.hashCode() ?: 0)
        result = 31 * result + (ql?.hashCode() ?: 0)
        result = 31 * result + (hl?.hashCode() ?: 0)
        result = 31 * result + (cdc?.hashCode() ?: 0)
        result = 31 * result + (hdc?.hashCode() ?: 0)
        result = 31 * result + (zd?.hashCode() ?: 0)
        result = 31 * result + (zhd?.hashCode() ?: 0)
        result = 31 * result + (qc?.hashCode() ?: 0)
        return result
    }


}