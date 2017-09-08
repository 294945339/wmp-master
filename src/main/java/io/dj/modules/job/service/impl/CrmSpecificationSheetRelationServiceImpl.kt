package io.dj.modules.job.service.impl

import com.xiaoleilu.hutool.util.BeanUtil
import io.dj.common.utils.JdbcConnectUtil
import io.dj.modules.goods.domain.BodyValueDomain
import io.dj.modules.goods.domain.ClothingDictDomain
import io.dj.modules.goods.domain.SpecificationSheetDomain
import io.dj.modules.goods.domain.SpecificationSheetRelationDomain
import io.dj.modules.goods.service.BodyValueService
import io.dj.modules.goods.service.ClothingDictService
import io.dj.modules.goods.service.SpecificationSheetRelationService
import io.dj.modules.goods.service.SpecificationSheetService
import io.dj.modules.job.bean.CrmSpecificationSheetRelationBean
import io.dj.modules.job.service.CrmSpecificationSheetRelationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/17
 * Time: 13:29
 * Email: 294945339@qq.com
 * 规格子表
 */
@Service("crmSpecificationSheetRelationService")
open class CrmSpecificationSheetRelationServiceImpl : CrmSpecificationSheetRelationService {

    @Autowired private val specificationSheetRelationService: SpecificationSheetRelationService? = null
    @Autowired private val specificationSheetService: SpecificationSheetService? = null
    @Autowired private val clothingDictService: ClothingDictService? = null
    @Autowired private val bodyValueService: BodyValueService? = null

    override fun updateCrmSpecificationSheetRelation() {
        //查询出系统中所有的父表
        val fatherDomains = specificationSheetService!!.queryList(HashMap<String, String>() as Map<String, Any>?)
        //查询系统中所有的子表
        val domains = specificationSheetRelationService!!.queryList(HashMap<String, String>() as Map<String, Any>?)
        //查询出视图中所有的子表
        val beans = getCrmSpecificationSheetRelation("", "")
        //将bean转成domain
        val newDomains = ArrayList<SpecificationSheetRelationDomain>()
        beans.mapTo(newDomains) { getDomainByMap(SpecificationSheetRelationDomain(), it) }

        for (newDomain in newDomains) {
            var isEq = false
            var num: Int? = 0
            for (i in domains!!.indices) {
                if (newDomain.crmId == domains[i].crmId) {
                    isEq = true
                    num = i
                    break
                }
            }
            if (isEq) {
                setSave(domains[num!!], newDomain, isEq, fatherDomains as ArrayList<SpecificationSheetDomain>)
                domains.removeAt(num)
            } else {
                setSave(SpecificationSheetRelationDomain(), newDomain, isEq, fatherDomains as ArrayList<SpecificationSheetDomain>)
            }
        }

        for (domain in domains) {
            specificationSheetRelationService.delete(domain.id)
        }
    }


    private fun setSave(domain: SpecificationSheetRelationDomain, newDomain: SpecificationSheetRelationDomain,
                        isEq: Boolean,fatherDomains:ArrayList<SpecificationSheetDomain>) {
        fatherDomains
                .filter { newDomain.fartherId == it.crmId }
                .forEach { newDomain.specificationSheet = it.id }
        if (isEq) {
            save(domain, newDomain)
        } else {
            save(SpecificationSheetRelationDomain(), newDomain)
        }
    }

    private fun save(domain: SpecificationSheetRelationDomain, bean: SpecificationSheetRelationDomain) {
        val bdIds = domain.bodyValueIds
        if(null != bdIds){
            for(id in bdIds){
                bodyValueService!!.delete(id)
            }
        }
        bean.id = domain.id
        BeanUtil.copyProperties(bean, domain)
        domain.sort = bean.sort!!.toInt()
        domain.createBy = 1L
        domain.updateBy = 1L
        //插入子表
        val ids = arrayOfNulls<Long>(bean.bodyValues.size)
        for (i in bean.bodyValues.indices) {
            val bodyValue = BodyValueDomain()
            bodyValue.createBy = 1L
            bodyValue.updateBy = 1L
            bodyValue.numericalValue = bean.bodyValues[i].numericalValue
            bodyValue.clothingDict = bean.bodyValues[i].clothingDict
            bodyValueService!!.save(bodyValue)
            ids[i] = bodyValue.id
        }
        domain.bodyValueIds = ids
        //插入子表后保存
        specificationSheetRelationService!!.save(domain)
    }

    /**
     * 查询crm里面SpecificationSheet
     */
    override fun getCrmSpecificationSheetRelation(id: String, fatherId: String): ArrayList<CrmSpecificationSheetRelationBean> {
        val con = JdbcConnectUtil().crmConnect
        var sql = "SELECT * from view_specification_sheet_relation"
        if ("" != id) {
            sql = "$sql where id = '$id'"
        }
        if ("" != fatherId) {
            sql = "$sql where father_id = '$fatherId'"
        }
        val ptst = con!!.prepareStatement(sql)
        val rs = ptst.executeQuery()
        val crmSpecificationSheetRelationBeans = ArrayList<CrmSpecificationSheetRelationBean>()
        while (rs.next()) {
            val bean = CrmSpecificationSheetRelationBean()
            bean.crmId = rs.getObject("id")?.toString()
            bean.code = rs.getObject("ggbdm")?.toString()
            bean.type = rs.getObject("hx")?.toString()
            if ("" == rs.getObject("gg")?.toString()) {
                bean.size = rs.getObject("cm")?.toString()
            } else {
                bean.size = rs.getObject("gg")?.toString()
            }
            bean.fartherId = rs.getObject("farther_id")?.toString()
            bean.sort = rs.getObject("xh")?.toString()

            bean.qyc = rs.getObject("qyc")?.toString()
            bean.hyc = rs.getObject("hyc")?.toString()
            bean.xw = rs.getObject("xw")?.toString()
            bean.zy = rs.getObject("zy")?.toString()
            bean.xb = rs.getObject("xb")?.toString()
            bean.jk = rs.getObject("jk")?.toString()
            bean.xc = rs.getObject("xc")?.toString()
            bean.xf = rs.getObject("xf")?.toString()
            bean.xk = rs.getObject("xk")?.toString()
            bean.yw = rs.getObject("yw")?.toString()
            bean.tw = rs.getObject("tw")?.toString()
            bean.kc = rs.getObject("kc")?.toString()
            bean.xfb = rs.getObject("xfb")?.toString()
            bean.hd = rs.getObject("hd")?.toString()
            bean.tw2 = rs.getObject("tw2")?.toString()
            bean.ql = rs.getObject("ql")?.toString()
            bean.hl = rs.getObject("hl")?.toString()
            bean.cdc = rs.getObject("cdc")?.toString()
            bean.hdc = rs.getObject("hdc")?.toString()
            bean.zd = rs.getObject("zd")?.toString()
            bean.zhd = rs.getObject("zhd")?.toString()
            bean.qc = rs.getObject("qc")?.toString()

            crmSpecificationSheetRelationBeans.add(bean)
        }
        rs.close()
        ptst.close()
        con.close()
        return crmSpecificationSheetRelationBeans
    }


    //把bean里的字段转成domain里面对象
    override fun getDomainByMap(domain: SpecificationSheetRelationDomain, bean: CrmSpecificationSheetRelationBean): SpecificationSheetRelationDomain {
        val bodyValues = ArrayList<BodyValueDomain>()
        domain.code = bean.code
        domain.sort = bean.sort!!.toInt()
        domain.size = bean.size
        domain.fartherId = bean.fartherId
        val nameAndType = bean.type?.split("/")
        if (nameAndType != null && nameAndType.isNotEmpty()) {
            domain.name = nameAndType[0]
            if (nameAndType.size > 1) {
                domain.type = nameAndType[1]
            }
        }
        domain.crmId = bean.crmId
        val dicts = clothingDictService!!.queryList(HashMap<String, String>() as Map<String, Any>)

        val map = getSSRMap()
        if(null != bean.qyc && "" != bean.qyc){
            setBodyAndValue(dicts, bean.qyc!!,bodyValues,map["qyc"])
        }
        if(null != bean.hyc && "" != bean.hyc){
            setBodyAndValue(dicts, bean.hyc!!,bodyValues,map["hyc"])
        }
        if(null != bean.xw && "" != bean.xw){
            setBodyAndValue(dicts, bean.xw!!,bodyValues,map["xw"])
        }
        if(null != bean.zy && "" != bean.zy){
            setBodyAndValue(dicts, bean.zy!!,bodyValues,map["zy"])
        }
        if(null != bean.xb && "" != bean.xb){
            setBodyAndValue(dicts, bean.xb!!,bodyValues,map["xb"])
        }
        if(null != bean.jk && "" != bean.jk){
            setBodyAndValue(dicts, bean.jk!!,bodyValues,map["jk"])
        }
        if(null != bean.xc && "" != bean.xc){
            setBodyAndValue(dicts, bean.xc!!,bodyValues,map["xc"])
        }
        if(null != bean.xf && "" != bean.xf){
            setBodyAndValue(dicts, bean.xf!!,bodyValues,map["xf"])
        }
        if(null != bean.xk && "" != bean.xk){
            setBodyAndValue(dicts, bean.xk!!,bodyValues,map["xk"])
        }
        if(null != bean.yw && "" != bean.yw){
            setBodyAndValue(dicts, bean.yw!!,bodyValues,map["yw"])
        }
        if(null != bean.tw && "" != bean.tw){
            setBodyAndValue(dicts, bean.tw!!,bodyValues,map["tw"])
        }
        if(null != bean.kc && "" != bean.kc){
            setBodyAndValue(dicts, bean.kc!!,bodyValues,map["kc"])
        }
        if(null != bean.xfb && "" != bean.xfb){
            setBodyAndValue(dicts, bean.xfb!!,bodyValues,map["xfb"])
        }
        if(null != bean.hd && "" != bean.hd){
            setBodyAndValue(dicts, bean.hd!!,bodyValues,map["hd"])
        }
        if(null != bean.tw2 && "" != bean.tw2){
            setBodyAndValue(dicts, bean.tw2!!,bodyValues,map["tw2"])
        }
        if(null != bean.ql && "" != bean.ql){
            setBodyAndValue(dicts, bean.ql!!,bodyValues,map["ql"])
        }
        if(null != bean.hl && "" != bean.hl){
            setBodyAndValue(dicts, bean.hl!!,bodyValues,map["hl"])
        }
        if(null != bean.cdc && "" != bean.cdc){
            setBodyAndValue(dicts, bean.cdc!!,bodyValues,map["cdc"])
        }
        if(null != bean.hdc && "" != bean.hdc){
            setBodyAndValue(dicts, bean.hdc!!,bodyValues,map["hdc"])
        }
        if(null != bean.zd && "" != bean.zd){
            setBodyAndValue(dicts, bean.zd!!,bodyValues,map["zd"])
        }
        if(null != bean.zhd && "" != bean.zhd){
            setBodyAndValue(dicts, bean.zhd!!,bodyValues,map["zhd"])
        }
        if(null != bean.qc && "" != bean.qc){
            setBodyAndValue(dicts, bean.qc!!,bodyValues, map["qc"])
        }
        domain.bodyValues = bodyValues
        return domain
    }

    private fun setBodyAndValue(dicts: MutableList<ClothingDictDomain>, numericalValue: String
                                , bodyValues: ArrayList<BodyValueDomain>, key: String?){
        val bodyValue = BodyValueDomain()
        val filter = dicts
                .filter { key == it.code }
        bodyValue.clothingDict = filter[0]
        bodyValue.numericalValue = numericalValue
        bodyValues.add(bodyValue)
    }

    //暴力反射还有问题
    private fun setBodyAndValues(code: String, bean: CrmSpecificationSheetRelationBean, bodyValues: ArrayList<BodyValueDomain>,
                                map: HashMap<String, String>, dicts: MutableList<ClothingDictDomain>) {
        var dictId: Long? = null
        dicts
                .filter { map[code]!! == it.code }
                .forEach { dictId = it.id }
        val field = bean.javaClass.getDeclaredField(code)
        field.isAccessible = true
        println("------------------->$code              ------------------------->$dictId")
        val numericalValue = field.get(bean).toString()
        if ("" != numericalValue) {
            val bodyValue = BodyValueDomain()
            val dict = ClothingDictDomain()
            dict.id = dictId
            println("2222222------------------->$code              ------------------------->$dictId    ---------------------->$numericalValue")
            bodyValue.clothingDict = dict
            bodyValue.numericalValue = numericalValue
            bodyValues.add(bodyValue)
        }
    }

    //创立字段对照map
    private fun getSSRMap(): HashMap<String, String> {
        var map = HashMap<String, String>()
        map.put("qyc", "before_long")
        map.put("hyc", "back_long")
        map.put("xw", "bust")
        map.put("zy", "middle_waisted")
        map.put("xb", "hem")
        map.put("jk", "shoulder_breadth")
        map.put("xc", "sleeve")
        map.put("xf", "sleeve_width")
        map.put("xk", "cuff")
        map.put("yw", "waist")
        map.put("tw", "hip")
        map.put("kc", "outseam")
        map.put("xfb", "sleeve_width_north")
        map.put("hd", "barre")
        map.put("tw2", "thigh")
        map.put("ql", "front_rise")
        map.put("hl", "back_rise")
        map.put("cdc", "side_pkt_length")
        map.put("hdc", "back_pocket_length")
        map.put("zd", "rise")
        map.put("zhd", "leg_width")
        map.put("qc", "skirt_length")
        return map
    }
}