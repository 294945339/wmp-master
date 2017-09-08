package io.dj.modules.job.service.impl

import com.xiaoleilu.hutool.util.BeanUtil
import io.dj.common.utils.JdbcConnectUtil
import io.dj.modules.crm.domain.OrderDomain
import io.dj.modules.crm.domain.OrderPeoplesDomain
import io.dj.modules.crm.service.CompanyService
import io.dj.modules.crm.service.OrderService
import io.dj.modules.job.bean.CrmOrderBean
import io.dj.modules.job.service.CrmOrderPeoplesService
import io.dj.modules.job.service.CrmOrderService
import io.dj.modules.measure.service.OrderPeoplesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.ArrayList


/**
 * @author  dj
 * @email 294945339@qq.com
 * @create 2017-08-24 14:07
 * @desc   量体通知单serviceImpl
 **/
@Service("crmOrderService")
open class CrmOrderServiceImpl : CrmOrderService {

    @Autowired private val orderService: OrderService? = null
    @Autowired private val companyService: CompanyService? = null
    @Autowired private val crmOrderPeoplesService: CrmOrderPeoplesService? = null

    override fun updateCrmOrder() {
        val beans = getCrmOrder("")
        val domains = orderService!!.queryList(HashMap<String, String>() as Map<String, Any>?) as? ArrayList<OrderDomain>
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
                setSave(OrderDomain(), bean, isEq)
            }
        }
        for (domain in domains!!) {
            orderService.delete(domain.id)
        }
    }

    override fun setSave(domain: OrderDomain, bean: CrmOrderBean, isEq: Boolean) {
        if (isEq) {
            if (
            !bean.originalNo.equals(domain.originalNo) ||
                    !bean.noticeNo.equals(domain.noticeNo) ||
                    !bean.type.equals(domain.type) ||
                    !bean.applyTime.equals(domain.applyTime) ||
                    !bean.measureTime.equals(domain.measureTime) ||
                    !bean.salesmanName.equals(domain.salesmanName) ||
                    !bean.salesmanPhone.equals(domain.salesmanPhone) ||
                    !bean.contactsName.equals(domain.contactsName) ||
                    !bean.contactsPhone.equals(domain.contactsPhone) ||
                    !bean.manNum.equals(domain.manNum) ||
                    !bean.womanNum.equals(domain.womanNum) ||
                    !bean.manConfig.equals(domain.manConfig) ||
                    !bean.womanConfig.equals(domain.womanConfig) ||
                    !bean.deliverTime.equals(domain.deliverTime) ||
                    !bean.measureType.equals(domain.measureType) ||
                    !bean.packingType.equals(domain.packingType) ||
                    !bean.packType.equals(domain.packType) ||
                    !bean.companyCrmId.equals(domain.company.crmId)
                    ) {
                save(domain, bean)
            }
        } else {
            save(OrderDomain(), bean)
        }
    }

    private fun save(domain: OrderDomain, bean: CrmOrderBean) {
        BeanUtil.copyProperties(bean, domain)
        domain.createBy = 1L
        domain.updateBy = 1L
        if (null != bean.companyCrmId) {
            val companys = companyService!!.queryList(object : HashMap<String, String>() {
                init {
                    put("crmId", bean.companyCrmId!!)
                }
            } as Map<String, Any>?)
            if (companys.size > 0) {
                domain.company = companys[0]
            }
        }
        if(null != domain.originalNo){
            //获取该条数据的人员名单
            val oldPeoples = domain.orderNameLists
            val peoples = crmOrderPeoplesService!!.getCrmOrderPeoples("",domain.originalNo)
            for(old in oldPeoples){
                for(new in peoples){
                    crmOrderPeoplesService.setSave(old,new,true)
                }
            }
        }
        orderService!!.save(domain)
    }

    /**
     * 查询erp里面goodsType
     */
    override fun getCrmOrder(id: String): java.util.ArrayList<CrmOrderBean> {
        val con = JdbcConnectUtil().crmConnect
        var sql = "SELECT * from view_order"
        if ("" != id) {
            sql = "$sql where id = '$id'"
        }
        val ptst = con!!.prepareStatement(sql)
        val rs = ptst.executeQuery()
        val crmOrderBeans = java.util.ArrayList<CrmOrderBean>()
        while (rs.next()) {
            val crmOrderBean = CrmOrderBean()
            crmOrderBean.crmId = rs.getObject("id")?.toString()
            crmOrderBean.originalNo = rs.getObject("original_no")?.toString()
            crmOrderBean.noticeNo = rs.getObject("notice_no")?.toString()
            crmOrderBean.type = rs.getObject("type")?.toString()
            crmOrderBean.applyTime = rs.getObject("apply_time")?.toString()
            crmOrderBean.measureTime = rs.getObject("measure_time")?.toString()
            crmOrderBean.salesmanName = rs.getObject("salesman_name")?.toString()
            crmOrderBean.salesmanPhone = rs.getObject("salesman_phone")?.toString()
            crmOrderBean.contactsName = rs.getObject("contacts_name")?.toString()
            crmOrderBean.contactsPhone = rs.getObject("contacts_phone")?.toString()
            crmOrderBean.companyCrmId = rs.getObject("company")?.toString()
            crmOrderBean.manNum = rs.getObject("man_num")?.toString()
            crmOrderBean.womanNum = rs.getObject("woman_num")?.toString()
            crmOrderBean.manConfig = rs.getObject("man_config")?.toString()
            crmOrderBean.womanConfig = rs.getObject("woman_config")?.toString()
            crmOrderBean.deliverTime = rs.getObject("deliver_time")?.toString()
            crmOrderBean.measureType = rs.getObject("measure_type")?.toString()
            crmOrderBean.packingType = rs.getObject("packing_type")?.toString()
            crmOrderBean.packType = rs.getObject("pack_type")?.toString()
            crmOrderBeans.add(crmOrderBean)
        }
        rs.close()
        ptst.close()
        con.close()
        return crmOrderBeans
    }
}