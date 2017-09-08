package io.dj.modules.job.service

import io.dj.modules.crm.domain.CompanyDomain
import io.dj.modules.job.bean.CrmCompanyBean
import java.util.ArrayList


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/15
 * Time: 11:35
 * Email: 294945339@qq.com
 */

interface CrmCompanyService {
    fun updateCrmCompany()
    fun getCrmCompany(id: String): ArrayList<CrmCompanyBean>
    fun setSave(domain: CompanyDomain, bean: CrmCompanyBean,isEq: Boolean)
}