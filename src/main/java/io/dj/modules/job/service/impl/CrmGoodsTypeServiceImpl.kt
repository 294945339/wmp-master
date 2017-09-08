package io.dj.modules.job.service.impl

import com.xiaoleilu.hutool.util.BeanUtil
import io.dj.common.utils.JdbcConnectUtil
import io.dj.modules.goods.domain.GoodsTypeDomain
import io.dj.modules.goods.service.GoodsTypeService
import io.dj.modules.job.bean.CrmGoodsTypeBean
import io.dj.modules.job.service.CrmGoodsTypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.ArrayList


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/14
 * Time: 10:03
 * Email: 294945339@qq.com
 * 商品类别同步
 */

@Service("crmGoodsTypeService")
open class CrmGoodsTypeServiceImpl : CrmGoodsTypeService {

    @Autowired private val goodsTypeService: GoodsTypeService? = null

    override fun updateCrmGoodsType() {
        val beans = getCrmGoodsType("")
        val domains = goodsTypeService!!.queryList(HashMap<String, String>() as Map<String, Any>?) as? ArrayList<GoodsTypeDomain>
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
                setSave(GoodsTypeDomain(), bean, isEq)
            }
        }
        for (domain in domains!!) {
            goodsTypeService.delete(domain.id)
        }
    }

    override fun setSave(domain: GoodsTypeDomain, bean: CrmGoodsTypeBean, isEq: Boolean) {
        if (isEq) {
            if (
            !bean.code.equals(domain.code) ||
                    !bean.crmName.equals(domain.crmName) ||
                    !bean.unit.equals(domain.unit) ||
                    !bean.typeStr.equals(domain.typeStr)
                    ) {
                saveCrmGoodsType(domain, bean)
            }
        } else {
            saveCrmGoodsType(GoodsTypeDomain(), bean)
        }
    }

    private fun saveCrmGoodsType(domain: GoodsTypeDomain, bean: CrmGoodsTypeBean) {
        BeanUtil.copyProperties(bean, domain)
        domain.createBy = 1L
        domain.updateBy = 1L
        goodsTypeService!!.save(domain)
    }

    /**
     * 查询erp里面goodsType
     */
    override fun getCrmGoodsType(id: String): ArrayList<CrmGoodsTypeBean> {
        val con = JdbcConnectUtil().crmConnect
        var sql = "SELECT * from view_goods_type"
        if (!"".equals(id)) {
            sql = "$sql where id = '$id'"
        }
        val ptst = con!!.prepareStatement(sql)
        val rs = ptst.executeQuery()
        val crmGoodsTypeBeans = ArrayList<CrmGoodsTypeBean>()
        while (rs.next()) {
            val crmGoodsTypeBean = CrmGoodsTypeBean()
            crmGoodsTypeBean.crmId = rs.getObject("id")?.toString()
            crmGoodsTypeBean.code = rs.getObject("code")?.toString()
            crmGoodsTypeBean.crmName = rs.getObject("name")?.toString()
            crmGoodsTypeBean.typeStr = rs.getObject("type_str")?.toString()
            crmGoodsTypeBean.unit = rs.getObject("unit")?.toString()
            crmGoodsTypeBeans.add(crmGoodsTypeBean)
        }
        rs.close()
        ptst.close()
        con.close()
        return crmGoodsTypeBeans
    }

}