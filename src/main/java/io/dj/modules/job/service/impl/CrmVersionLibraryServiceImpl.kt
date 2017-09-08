package io.dj.modules.job.service.impl

import com.xiaoleilu.hutool.util.BeanUtil
import io.dj.common.utils.JdbcConnectUtil
import io.dj.modules.goods.domain.VersionLibraryDomain
import io.dj.modules.goods.service.SpecificationSheetService
import io.dj.modules.goods.service.VersionLibraryService
import io.dj.modules.job.bean.CrmVersionLibraryBean
import io.dj.modules.job.service.CrmVersionLibraryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.ArrayList

@Service("crmVersionLibraryService")
open class CrmVersionLibraryServiceImpl : CrmVersionLibraryService {

    @Autowired private val versionLibraryService: VersionLibraryService? = null
    @Autowired private val specificationSheetService: SpecificationSheetService? = null

    override fun updateCrmVersionLibrary() {
        val beans = getCrmVersionLibrary("")
        val domains = versionLibraryService!!.queryList(HashMap<String, String>() as Map<String, Any>?) as? ArrayList<VersionLibraryDomain>
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
                setSave(VersionLibraryDomain(), bean, isEq)
            }
        }
        for (domain in domains!!) {
            versionLibraryService.delete(domain.id)
        }
    }

    override fun setSave(domain: VersionLibraryDomain, bean: CrmVersionLibraryBean, isEq: Boolean) {
        if ("" != bean.specificationSheetCode && null != bean.specificationSheetCode) {
            val list = specificationSheetService!!.queryList(object : HashMap<String, String>() {
                init {
                    put("code", bean.specificationSheetCode!!)
                }
            }, false)
            if (list.isNotEmpty()) {
                bean.specificationSheet  = list[0]
            }
        }
        if (isEq) {
            val beanId = bean.specificationSheet?.id
            val domainId = domain.specificationSheet?.id
            if (
            !bean.code.equals(domain.code) ||
                    !bean.code.equals(domain.code) ||
                    !bean.oldCode.equals(domain.oldCode) ||
                    !bean.type.equals(domain.type) ||
                    beanId != domainId
                    ) {
                saveCrmCompany(domain, bean)
            }
        } else {
            saveCrmCompany(VersionLibraryDomain(), bean)
        }
    }

    private fun saveCrmCompany(domain: VersionLibraryDomain, bean: CrmVersionLibraryBean) {
        BeanUtil.copyProperties(bean, domain)
        bean.specificationSheet?.id = domain.specificationSheet.id
        domain.createBy = 1L
        domain.updateBy = 1L
        versionLibraryService!!.save(domain)
    }

    /**
     * 查询crm
     */
    override fun getCrmVersionLibrary(id: String): ArrayList<CrmVersionLibraryBean> {
        val con = JdbcConnectUtil().crmConnect
        var sql = "SELECT * from view_version_library"
        if ("" != id) {
            sql = "$sql where id = '$id'"
        }
        val ptst = con!!.prepareStatement(sql)
        val rs = ptst.executeQuery()
        val crmVersionLibraryBeans = ArrayList<CrmVersionLibraryBean>()
        while (rs.next()) {
            val crmVersionLibraryBean = CrmVersionLibraryBean()
            crmVersionLibraryBean.crmId = rs.getObject("id")?.toString()
            crmVersionLibraryBean.code = rs.getObject("code")?.toString()
            crmVersionLibraryBean.oldCode = rs.getObject("old_code")?.toString()
            crmVersionLibraryBean.type = rs.getObject("type")?.toString()
            crmVersionLibraryBean.specificationSheetCode = rs.getObject("specification_sheet")?.toString()
            crmVersionLibraryBeans.add(crmVersionLibraryBean)
        }
        rs.close()
        ptst.close()
        con.close()
        return crmVersionLibraryBeans
    }
}