package io.dj.modules.job.bean

/**
 * @author  dj
 * @email 294945339@qq.com
 * @create 2017-08-24 13:58
 * @desc      crm量体通知单
 **/

open class CrmOrderBean {

    var crmId: String? = null
    var originalNo: String? = null          //原始订单编号
    var noticeNo: String? = null            //通知订单编号
    var type: String? = null                //量体类型
    var applyTime: String? = null           //申请时间
    var measureTime: String? = null         //量体时间
    var salesmanName: String? = null        //业务员
    var salesmanPhone: String? = null       //业务员联系号码
    var contactsName: String? = null        //联系人
    var contactsPhone: String? = null        //联系人电话
    var companyCrmId: String? = null       //量体单位
    var manNum: String? = null              //男人数
    var womanNum: String? = null            //女人数
    var manConfig: String? = null           //男人配置
    var womanConfig: String? = null         //女人配置
    var deliverTime: String? = null           //交付时间
    var measureType: String? = null           //量体方式
    var packingType: String? = null           //装箱要求
    var packType: String? = null            //打包要求

}