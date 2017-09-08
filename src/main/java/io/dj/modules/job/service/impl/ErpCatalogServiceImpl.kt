package io.dj.modules.job.service.impl

import com.xiaoleilu.hutool.util.BeanUtil
import io.dj.common.utils.JdbcConnectUtil
import io.dj.modules.goods.domain.GoodsCatalogDomain
import io.dj.modules.goods.service.GoodsCatalogService
import io.dj.modules.job.bean.ErpGoodsCatalogBean
import io.dj.modules.job.service.ErpGoodsCatalogService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.ArrayList

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * email 294945339@qq.com
 * Date: 2017/8/11
 * Description: 商品目录
 **/
@Service("erpTaskService")
open class ErpCatalogServiceImpl : ErpGoodsCatalogService {

    @Autowired private val goodsCatalogService: GoodsCatalogService? = null

    override fun updateErpGoodsCatalog() {
        val beans = getErpGoodsCatalog()
        val domains = goodsCatalogService!!.queryList(HashMap<String, String>() as Map<String, Any>?) as ArrayList<GoodsCatalogDomain>
        for (erpGoodsCatalogBean in beans) {
            var isEq = false
            var num: Int? = null
            for (i in domains.indices) {
                if (domains[i].erpId == erpGoodsCatalogBean.erpId) {
                    isEq = true
                    num = i
                    break
                }
            }
            if (isEq) {
                if (
                !erpGoodsCatalogBean.code.equals(domains[num!!].code) ||
                        !erpGoodsCatalogBean.name.equals(domains[num].name) ||
                        !erpGoodsCatalogBean.type.equals(domains[num].type) ||
                        !erpGoodsCatalogBean.typeName.equals(domains[num].typeName) ||
                        !erpGoodsCatalogBean.unit.equals(domains[num].unit)
                        ) {
                    saveErpGoodsCatalog(domains[num], erpGoodsCatalogBean)
                }
                domains.removeAt(num)
            } else {
                saveErpGoodsCatalog(GoodsCatalogDomain(), erpGoodsCatalogBean)
            }
        }
        for (domain in domains!!) {
            goodsCatalogService.delete(domain.id)
        }
    }


    private fun saveErpGoodsCatalog(domain: GoodsCatalogDomain, bean: ErpGoodsCatalogBean) {
        BeanUtil.copyProperties(bean, domain)
        domain.createBy = 1L
        domain.updateBy = 1L
        goodsCatalogService!!.save(domain)
    }

    /**
     * 查询dap里面office
     */
    private fun getErpGoodsCatalog(): ArrayList<ErpGoodsCatalogBean> {
        val con = JdbcConnectUtil().iposConnect
        val sql = "SELECT * from view_goods_catalog"
        val ptst = con!!.prepareStatement(sql)
        val rs = ptst.executeQuery()
        val erpGoodsCatalogBeans = ArrayList<ErpGoodsCatalogBean>()
        while (rs.next()) {
            val erpGoodsCatalogBean = ErpGoodsCatalogBean()
            erpGoodsCatalogBean.erpId = rs.getObject("id")?.toString()
            erpGoodsCatalogBean.code = rs.getObject("code")?.toString()
            erpGoodsCatalogBean.name = rs.getObject("name")?.toString()
            erpGoodsCatalogBean.unit = rs.getObject("unit")?.toString()
            erpGoodsCatalogBean.type = rs.getObject("type")?.toString()
            erpGoodsCatalogBean.typeName = rs.getObject("type_name")?.toString()
            erpGoodsCatalogBeans.add(erpGoodsCatalogBean)
        }
        rs.close()
        ptst.close()
        con.close()
        return erpGoodsCatalogBeans
    }
}