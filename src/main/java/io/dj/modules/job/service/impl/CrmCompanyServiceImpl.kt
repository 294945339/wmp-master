package io.dj.modules.job.service.impl

import com.xiaoleilu.hutool.util.BeanUtil
import io.dj.common.utils.JdbcConnectUtil
import io.dj.modules.crm.domain.CompanyDomain
import io.dj.modules.crm.service.CompanyService
import io.dj.modules.job.bean.CrmCompanyBean
import io.dj.modules.job.service.CrmCompanyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.ArrayList


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/15
 * Time: 11:35
 * Email: 294945339@qq.com
 * 企业客户同步
 */
@Service("crmCompanyService")
open class CrmCompanyServiceImpl : CrmCompanyService {

    @Autowired private val companyService: CompanyService? = null

    override fun updateCrmCompany() {
        val beans = getCrmCompany("")
        val domains = companyService!!.queryList(HashMap<String, String>() as Map<String, Any>?) as? ArrayList<CompanyDomain>
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
                setSave(CompanyDomain(), bean, isEq)
            }
        }
        for (domain in domains!!) {
            companyService.delete(domain.id)
        }
    }


    override fun setSave(domain: CompanyDomain, bean: CrmCompanyBean, isEq: Boolean) {
        if (isEq) {
            if (
            !bean.code.equals(domain.code) ||
                    !bean.name.equals(domain.name) ||
                    !bean.phone.equals(domain.phone) ||
                    !bean.salesmanName.equals(domain.salesmanName) ||
                    !bean.salesmanPhone.equals(domain.salesmanPhone) ||
                    !bean.address.equals(domain.address)
                    ) {
                saveCrmCompany(domain, bean)
            }
        } else {
            saveCrmCompany(CompanyDomain(), bean)
        }
    }

    private fun saveCrmCompany(domain: CompanyDomain, bean: CrmCompanyBean) {
        BeanUtil.copyProperties(bean, domain)
        domain.createBy = 1L
        domain.updateBy = 1L
        companyService!!.save(domain)
    }

    /**
     * 查询erp里面goodsType
     */
    override fun getCrmCompany(id: String): ArrayList<CrmCompanyBean> {
        val con = JdbcConnectUtil().crmConnect
        var sql = "SELECT * from view_company"
        if ("" != id) {
            sql = "$sql where id = '$id'"
        }
        val ptst = con!!.prepareStatement(sql)
        val rs = ptst.executeQuery()
        val crmCompanyBeans = ArrayList<CrmCompanyBean>()
        while (rs.next()) {
            val crmCompanyBean = CrmCompanyBean()
            crmCompanyBean.crmId = rs.getObject("id")?.toString()
            crmCompanyBean.name = rs.getObject("name")?.toString()
            crmCompanyBean.code = rs.getObject("code")?.toString()
            crmCompanyBean.address = rs.getObject("address")?.toString()
            crmCompanyBean.phone = rs.getObject("phone")?.toString()
            crmCompanyBean.salesmanName = rs.getObject("salesman_name")?.toString()
            crmCompanyBean.salesmanPhone = rs.getObject("salesman_phone")?.toString()
            crmCompanyBeans.add(crmCompanyBean)
        }
        rs.close()
        ptst.close()
        con.close()
        return crmCompanyBeans
    }
}