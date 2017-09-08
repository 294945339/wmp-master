package io.dj.modules.job.service.impl

import com.xiaoleilu.hutool.date.DateUtil
import com.xiaoleilu.hutool.util.BeanUtil
import io.dj.common.base.enums.TaskStatusEnum
import io.dj.common.utils.JdbcConnectUtil
import io.dj.modules.crm.service.CompanyService
import io.dj.modules.crm.service.OrderService
import io.dj.modules.job.bean.CrmMeasureDispatchBean
import io.dj.modules.job.service.CrmMeasureDispatchService
import io.dj.modules.measure.domain.MeasureDispatchDomain
import io.dj.modules.measure.service.MeasureDispatchService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


/**
 * @author  dj
 * @email 294945339@qq.com
 * @create 2017-08-25 15:02
 * @desc
 **/

@Service("crmMeasureDispatchService")
open class CrmMeasureDispatchServiceImpl:CrmMeasureDispatchService {

    @Autowired private val measureDispatchService : MeasureDispatchService? = null
    @Autowired private val companyService: CompanyService? = null
    @Autowired private val orderService : OrderService? = null

    override fun updateCrmMeasureDispatch() {
        val beans = getCrmMeasureDispatch("")
        val domains = measureDispatchService!!.queryList(HashMap<String, String>() as Map<String, Any>?) as? ArrayList<MeasureDispatchDomain>
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
                setSave(MeasureDispatchDomain(), bean, isEq)
            }
        }

        for (domain in domains!!) {
            measureDispatchService.delete(domain.id)
        }
    }


    override fun setSave(domain: MeasureDispatchDomain, bean: CrmMeasureDispatchBean, isEq: Boolean) {
        if (isEq) {
            if (
            !bean.code.equals(domain.code) ||
                    !bean.type.equals(domain.type)||
                    !bean.taskTime.equals(domain.taskTime)
                    ) {
                saveCrmCompany(domain, bean)
            }
        } else {
            saveCrmCompany(MeasureDispatchDomain(), bean)
        }
    }

    private fun saveCrmCompany(domain: MeasureDispatchDomain, bean: CrmMeasureDispatchBean) {
        BeanUtil.copyProperties(bean, domain)
        if(null !=  bean.companyCrmId){
            val companys = companyService!!.queryList(object : HashMap<String,String>(){
                init {
                    put("crmId", bean.companyCrmId!!)
                }
            } as Map<String, Any>?)
            if(companys.size > 0){
                domain.company = companys[0]
            }
        }

        if(null != bean.orderCrmCode ){
            val orders = orderService!!.queryList(object : HashMap<String,String>(){
                init {
                    put("noticeNo", bean.orderCrmCode!!)
                }
            } as Map<String, Any>?)
            if(orders.size > 0){
                domain.order = orders[0]
            }
        }
        val tastDateStr = domain.taskTime
        val taskDate = DateUtil.parse(tastDateStr)//,"yyyy-MM-dd HH:mm:ss:sss")
        val endDate = DateUtil.parse("2017-09-01")
        if(taskDate.time > endDate.time){
            domain.taskStatus = TaskStatusEnum.Do
        }else{
            domain.taskStatus = TaskStatusEnum.Other
        }
        domain.createBy = 1L
        domain.updateBy = 1L
        measureDispatchService!!.save(domain)
    }


    /**
     * 查询crm
     */
    override fun getCrmMeasureDispatch(id: String): ArrayList<CrmMeasureDispatchBean> {
        val con = JdbcConnectUtil().crmConnect
        var sql = "SELECT * from qdpqd2_view"
        if ("" != id) {
            sql = "$sql where id = '$id'"
        }
        val ptst = con!!.prepareStatement(sql)
        val rs = ptst.executeQuery()
        val crmMeasureDispatchBeans = ArrayList<CrmMeasureDispatchBean>()
        while (rs.next()) {
            val crmVersionLibraryBean = CrmMeasureDispatchBean()
            crmVersionLibraryBean.crmId = rs.getObject("id")?.toString()
            crmVersionLibraryBean.code = rs.getObject("pqdh")?.toString()
            crmVersionLibraryBean.type = rs.getObject("ltfs")?.toString()
            crmVersionLibraryBean.orderCrmCode = rs.getObject("gllttzd")?.toString()
            crmVersionLibraryBean.companyCrmId = rs.getObject("ltdw_id")?.toString()
            crmVersionLibraryBean.taskTime = rs.getObject("cfsj")?.toString()
            crmMeasureDispatchBeans.add(crmVersionLibraryBean)
        }
        rs.close()
        ptst.close()
        con.close()
        return crmMeasureDispatchBeans
    }
}