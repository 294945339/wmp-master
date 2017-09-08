package io.dj.modules.job.service.impl

import io.dj.modules.job.service.DapOfficeTaskService
import org.springframework.stereotype.Service
import com.xiaoleilu.hutool.setting.dialect.Props
import io.dj.common.utils.JdbcConnectUtil
import io.dj.modules.sys.entity.SysDeptEntity
import io.dj.modules.sys.service.SysDeptService
import io.dj.modules.job.bean.DapOfficeBean
import org.springframework.beans.factory.annotation.Autowired
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.util.ArrayList


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/4
 * 组织架构同步
 **/
@Service("deptTaskService")
open class DapOfficeTaskServiceImpl : DapOfficeTaskService {

    @Autowired
    private val sysDeptService: SysDeptService? = null

    /**
     * 同步dept数据库
     */
    override fun updateDapOffice() {
        val officeList = getDapOffices()
        var grade = 0

        /**
         * 获取树状编码
         */
        officeList.indices.filter {
            "0".equals(officeList[it].delFlag)
        }.forEach {
            if (officeList[it].grade!!.toInt() > grade) {
                grade = officeList[it].grade!!.toInt()
            }
        }

        /**
         * 按编码筛选数据
         */
        for (i in 1..grade) {
            officeList.indices
                    .filter {
                        i.toString().equals(officeList[it].grade)
                        "0".equals(officeList[it].delFlag)
                    }
                    .forEach {
                        System.out.println(officeList[it].grade + "---->" + officeList[it].name + "---->" + grade)
                        saveDept(officeList[it])
                    }
        }

        /**
         * 按类型删除数据
         */
        officeList.indices
                .filter {
                    "1".equals(officeList[it].delFlag)
                }.forEach {
            delDept(officeList[it])
        }
    }

    /**
     * 删除到系统dept
     */
    private fun delDept(office: DapOfficeBean) {
        var map = HashMap<String, String>()
        map.put("dapId", office.id!!)
        val depts = sysDeptService!!.queryList(map as Map<String, Any>?)
        if (depts.size > 0) {
            sysDeptService.delete(depts[0].id)
        }
    }

    /**
     * 新增,修改到系统dept
     */
    private fun saveDept(office: DapOfficeBean) {
        var map = HashMap<String, String>()
        map.put("dapId", office.id!!)
        val depts = sysDeptService!!.queryList(map as Map<String, Any>?)

        if (depts.size > 0) {
            var dept = setDept(depts[0], office)
            dept.updateBy = 1L
            sysDeptService!!.update(dept)
        } else {
            var dept = setDept(SysDeptEntity(), office)
            dept.createBy = 1L
            sysDeptService!!.save(dept)
        }
    }

    /**
     * 查询dap里面office
     */
    private fun getDapOffices(): ArrayList<DapOfficeBean> {
        val con = JdbcConnectUtil().dapConnect
        var ptst: PreparedStatement? = null
        var rs: ResultSet? = null
        val sql = "select grade,id,name,parent_id,parent_ids,del_flag from sys_office"
        ptst = con!!.prepareStatement(sql)
        rs = ptst.executeQuery()
        val officeList = ArrayList<DapOfficeBean>()
        while (rs.next()) {
            val dapOfficeBean = DapOfficeBean()
            dapOfficeBean.grade = rs.getObject("grade")?.toString()
            dapOfficeBean.id = rs.getObject("id")?.toString()
            dapOfficeBean.name = rs.getObject("name")?.toString()
            dapOfficeBean.parentId = rs.getObject("parent_id")?.toString()
            dapOfficeBean.parentIds = rs.getObject("parent_ids")?.toString()
            dapOfficeBean.delFlag = rs.getObject("del_flag")?.toString()
            officeList.add(dapOfficeBean)
        }
        rs.close()
        ptst.close()
        con.close()

        return officeList
    }


    private fun setDept(dept: SysDeptEntity, office: DapOfficeBean): SysDeptEntity {
        dept.name = office.name
        dept.dapId = office.id
        dept.orderNum = 0
        val ids = office.parentIds?.split(",") as ArrayList<String>
        ids.indices
                .filter { "" == ids[it] }
                .forEach { ids.removeAt(it) }
        val idLongs = arrayOfNulls<Long>(ids.size)
        for (i in ids.indices) {
            if ("0".equals(ids[i])) {
                idLongs[i] = java.lang.Long.valueOf(ids[i])
            } else {
                var searchMap = HashMap<String, String>()
                searchMap.put("dapId", ids[i])
                val deptList = sysDeptService!!.queryList(searchMap as Map<String, Any>?)
                if (deptList.size > 0) {
                    idLongs[i] = java.lang.Long.valueOf(deptList[0].id)
                }
            }
        }
        if ("1".equals(office.grade)) {
            dept.parentId = office.parentId?.toLong()
        } else {
            var searchMap = HashMap<String, String>()
            searchMap.put("dapId", office.parentId!!)
            val deptList = sysDeptService!!.queryList(searchMap as Map<String, Any>?)
            if (deptList.size > 0) {
                dept.parentId = deptList[0].id.toLong()
            }
        }
        dept.parentIds = idLongs
        return dept
    }
}