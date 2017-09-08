package io.dj.modules.job.service.impl

import com.xiaoleilu.hutool.util.BeanUtil
import io.dj.common.base.enums.SexEnum
import io.dj.common.utils.JdbcConnectUtil
import io.dj.modules.goods.domain.SpecificationSheetDomain
import io.dj.modules.goods.enums.SpecificationSheetTypeEnum
import io.dj.modules.goods.service.SpecificationSheetService
import io.dj.modules.job.bean.CrmSpecificationSheetBean
import io.dj.modules.job.service.CrmSpecificationSheetRelationService
import io.dj.modules.job.service.CrmSpecificationSheetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.ArrayList


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/15
 * Time: 15:06
 * Email: 294945339@qq.com
 * 规格父表
 */
@Service("crmSpecificationSheetService")
open class CrmSpecificationSheetServiceImpl : CrmSpecificationSheetService {

    @Autowired private val specificationSheetService: SpecificationSheetService? = null
    @Autowired private val crmSpecificationSheetRelationService : CrmSpecificationSheetRelationService? = null

    override fun updateAll(){
        //更新父表
        updateCrmSpecificationSheet()
        //查询出所有的主表内容
        crmSpecificationSheetRelationService!!.updateCrmSpecificationSheetRelation()
    }

    override fun updateCrmSpecificationSheet() {
        val beans = getCrmSpecificationSheet("")
        val domains = specificationSheetService!!.queryList(HashMap<String, String>() as Map<String, Any>?) as? ArrayList<SpecificationSheetDomain>
        for (bean in beans) {
            var isEq = false
            var num: Int? = null
            for (i in domains!!.indices) {
                if (bean.crmId.equals(domains[i].crmId)) {
                    isEq = true
                    num = i
                    break
                }
            }
            if (isEq) {
                setSave(domains[num!!], bean, isEq)
                domains.removeAt(num)
            } else {
                setSave(SpecificationSheetDomain(), bean, isEq)
            }
        }

        for(domain in domains!!){
            specificationSheetService.delete(domain.id)
        }
    }

    override fun setSave(domain: SpecificationSheetDomain, bean: CrmSpecificationSheetBean, isEq: Boolean) {
        if (isEq) {
            if (
            !bean.code.equals(domain.code) ||
                    !bean.name.equals(domain.name) ||
                     bean.sex!=(domain.sex) ||
                    !bean.fileCode.equals(domain.fileCode) ||
                    !bean.versionName.equals(domain.versionName) ||
                    bean.type!! != domain.type
                    ) {
                saveSpecificationSheet(domain, bean)
            }
        } else {
            saveSpecificationSheet(SpecificationSheetDomain(), bean)
        }
    }

    private fun saveSpecificationSheet(domain: SpecificationSheetDomain, bean: CrmSpecificationSheetBean) {
        BeanUtil.copyProperties(bean, domain)
        domain.createBy = 1L
        domain.updateBy = 1L
        specificationSheetService!!.save(domain)
    }

    /**
     * 查询erp里面goodsType
     */
    override fun getCrmSpecificationSheet(id: String): ArrayList<CrmSpecificationSheetBean> {
        val con = JdbcConnectUtil().crmConnect
        var sql = "SELECT * from view_specification_sheet"
        if ("" != id) {
            sql = "$sql where id = '$id'"
        }
        val ptst = con!!.prepareStatement(sql)
        val rs = ptst.executeQuery()
        val crmSpecificationSheetBeans = ArrayList<CrmSpecificationSheetBean>()
        while (rs.next()) {
            val crmSpecificationSheetBean = CrmSpecificationSheetBean()
            crmSpecificationSheetBean.crmId = rs.getObject("id")?.toString()
            crmSpecificationSheetBean.code = rs.getObject("code")?.toString()
            crmSpecificationSheetBean.name = rs.getObject("name")?.toString()
            crmSpecificationSheetBean.canUse = "1" == rs.getObject("can_use")?.toString()
            val sex = rs.getObject("sex")?.toString()
            if ("1" == sex) {
                crmSpecificationSheetBean.sex = SexEnum.man
            } else {
                crmSpecificationSheetBean.sex = SexEnum.woman
            }
            val typeStr =  rs.getObject("type")?.toString()

            SpecificationSheetTypeEnum.values()
                    .filter { it.getName() == typeStr }
                    .forEach { crmSpecificationSheetBean.type = it }

            crmSpecificationSheetBean.fileCode = rs.getObject("file_code")?.toString()
            crmSpecificationSheetBean.versionName = rs.getObject("version_name")?.toString()
            crmSpecificationSheetBeans.add(crmSpecificationSheetBean)
        }
        rs.close()
        ptst.close()
        con.close()
        return crmSpecificationSheetBeans
    }

}