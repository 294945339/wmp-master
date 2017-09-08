package io.dj.modules.job.service.impl

import com.xiaoleilu.hutool.util.BeanUtil
import io.dj.common.utils.JdbcConnectUtil
import io.dj.modules.goods.domain.SizeSheetDomain
import io.dj.modules.goods.service.SizeSheetService
import io.dj.modules.job.bean.ErpSizeSheetBean
import io.dj.modules.job.service.ErpSizeSheetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.ArrayList

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * email 294945339@qq.com
 * Date: 2017/8/11
 * Description: 尺码表
 **/
@Service("erpSizeSheetServiceImpl")
open class ErpSizeSheetServiceImpl : ErpSizeSheetService {

    @Autowired private val sizeSheetService: SizeSheetService? = null

    override fun updateErpSizeSheet() {

        val beans = getErpSizeSheet()
        val domains = sizeSheetService!!.queryList(HashMap<String, String>() as Map<String, Any>?)

        for (erpSizeSheetBean in beans) {
            var isEq = false
            var num: Int? = null
            for (i in domains.indices) {
                if (domains[i].erpId == erpSizeSheetBean.erpId) {
                    isEq = true
                    num = i
                    break
                }
            }
            if (isEq) {
                if (
                !erpSizeSheetBean.code.equals(domains[0].code) ||
                        !erpSizeSheetBean.name.equals(domains[0].name)
                        ) {
                    saveErpSizeSheet(domains[num!!], erpSizeSheetBean)
                }
                domains.removeAt(num!!)
            } else {
                saveErpSizeSheet(SizeSheetDomain(), erpSizeSheetBean)
            }
        }
        for (domain in domains!!) {
            sizeSheetService.delete(domain.id)
        }
    }

    private fun saveErpSizeSheet(domain: SizeSheetDomain, bean: ErpSizeSheetBean) {
        BeanUtil.copyProperties(bean, domain)
        domain.createBy = 1L
        domain.updateBy = 1L
        sizeSheetService!!.save(domain)
    }

    /**
     * 查询erp里面goodsCatalog
     */
    private fun getErpSizeSheet(): ArrayList<ErpSizeSheetBean> {
        val con = JdbcConnectUtil().iposConnect
        val sql = "SELECT * from view_size_sheet"
        val ptst = con!!.prepareStatement(sql)
        val rs = ptst.executeQuery()
        val erpSizeSheetBeans = ArrayList<ErpSizeSheetBean>()
        while (rs.next()) {
            val erpSizeSheetBean = ErpSizeSheetBean()
            erpSizeSheetBean.erpId = rs.getObject("id")?.toString()
            erpSizeSheetBean.code = rs.getObject("code")?.toString()
            erpSizeSheetBean.name = rs.getObject("name")?.toString()
            erpSizeSheetBeans.add(erpSizeSheetBean)
        }
        rs.close()
        ptst.close()
        con.close()
        return erpSizeSheetBeans
    }
}