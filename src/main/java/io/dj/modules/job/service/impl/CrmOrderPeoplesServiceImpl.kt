package io.dj.modules.job.service.impl

import com.xiaoleilu.hutool.util.BeanUtil
import io.dj.common.base.enums.SexEnum
import io.dj.common.utils.JdbcConnectUtil
import io.dj.modules.crm.domain.OrderPeoplesDomain
import io.dj.modules.goods.service.ClothingDictService
import io.dj.modules.goods.service.GoodsTypeService
import io.dj.modules.job.bean.CrmOrderPeoplesBean
import io.dj.modules.job.service.CrmOrderPeoplesService
import io.dj.modules.measure.service.OrderPeoplesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.ArrayList

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * email 294945339@qq.com
 * Date: 2017/8/26
 * Description:
 **/
@Service("crmOrderPeoplesService")
open class CrmOrderPeoplesServiceImpl : CrmOrderPeoplesService {

    @Autowired private val orderPeoplesService: OrderPeoplesService? = null

    override fun updateCrmOrderPeoples(fatherCode: String) {
        val beans = getCrmOrderPeoples("", fatherCode)
        val domains = orderPeoplesService!!.queryList(HashMap<String, String>() as Map<String, Any>?) as? ArrayList<OrderPeoplesDomain>
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
                setSave(OrderPeoplesDomain(), bean, isEq)
            }
        }
        for (domain in domains!!) {
            orderPeoplesService.delete(domain.id)
        }
    }

    override fun setSave(domain: OrderPeoplesDomain, bean: CrmOrderPeoplesBean, isEq: Boolean) {
        if (isEq) {
            if (
            bean.code != domain.code ||
                    bean.name != domain.name ||
                    bean.dep != domain.dep ||
                    bean.sex != domain.sex ||
                    bean.jacketValue != domain.jacketValue ||
                    bean.trousersValue != domain.trousersValue ||
                    bean.vestValue != domain.vestValue ||
                    bean.shirtValue != domain.shirtValue ||
                    bean.skirtValue != domain.skirtValue ||
                    bean.overcoatValue != domain.overcoatValue
                    ) {
                save(domain, bean)
            }
        } else {
            save(OrderPeoplesDomain(), bean)
        }
    }

    override fun setSave(domain: OrderPeoplesDomain, bean: OrderPeoplesDomain, isEq: Boolean) {
        if (isEq) {
            if (
            bean.code != domain.code ||
                    bean.name != domain.name ||
                    bean.dep != domain.dep ||
                    bean.sex != domain.sex ||
                    bean.jacketValue != domain.jacketValue ||
                    bean.trousersValue != domain.trousersValue ||
                    bean.vestValue != domain.vestValue ||
                    bean.shirtValue != domain.shirtValue ||
                    bean.skirtValue != domain.skirtValue ||
                    bean.overcoatValue != domain.overcoatValue
                    ) {
                save(domain, bean)
            }
        } else {
            save(OrderPeoplesDomain(), bean)
        }
    }

    private fun save(domain: OrderPeoplesDomain, bean: Any) {
        BeanUtil.copyProperties(bean, domain)
        domain.createBy = 1L
        domain.updateBy = 1L
        orderPeoplesService!!.save(domain)
    }


    /**
     * 查询erp里面人员名单
     */
    override fun getCrmOrderPeoples(id: String, fatherCode: String): java.util.ArrayList<CrmOrderPeoplesBean> {
        val con = JdbcConnectUtil().crmConnect
        var sql = "SELECT * from qdltry_view"
        if ("" != id) {
            sql = "$sql where id = '$id'"
        }
        if ("" != fatherCode && "" == id) {
            sql = "$sql where glxdb = '$fatherCode'"
        }
        val ptst = con!!.prepareStatement(sql)
        val rs = ptst.executeQuery()
        val crmOrderPeoplesBeans = java.util.ArrayList<CrmOrderPeoplesBean>()
        while (rs.next()) {
            val crmOrderPeoplesBean = CrmOrderPeoplesBean()
            crmOrderPeoplesBean.crmId = rs.getObject("id")?.toString()
            crmOrderPeoplesBean.name = rs.getObject("sname")?.toString()
            crmOrderPeoplesBean.code = rs.getObject("code")?.toString()
            crmOrderPeoplesBean.dep = rs.getObject("dep")?.toString()
            if ("女" == rs.getObject("sex")?.toString()) {
                crmOrderPeoplesBean.sex = SexEnum.woman
            } else {
                crmOrderPeoplesBean.sex = SexEnum.man
            }
            crmOrderPeoplesBean.fatherCode = rs.getObject("glxdb")?.toString()
            crmOrderPeoplesBean.jacketValue = rs.getObject("sy")?.toString()
            crmOrderPeoplesBean.trousersValue = rs.getObject("kz")?.toString()
            crmOrderPeoplesBean.vestValue = rs.getObject("mj")?.toString()
            crmOrderPeoplesBean.shirtValue = rs.getObject("cs")?.toString()
            crmOrderPeoplesBean.skirtValue = rs.getObject("qz")?.toString()
            crmOrderPeoplesBean.overcoatValue = rs.getObject("dy")?.toString()
            crmOrderPeoplesBeans.add(crmOrderPeoplesBean)
        }
        rs.close()
        ptst.close()
        con.close()
        return crmOrderPeoplesBeans
    }
}