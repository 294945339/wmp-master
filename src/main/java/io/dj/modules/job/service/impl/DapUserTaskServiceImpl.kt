package io.dj.modules.job.service.impl

import io.dj.common.utils.JdbcConnectUtil
import io.dj.modules.job.bean.DapUserBean
import io.dj.modules.job.service.DapUserTaskService
import io.dj.modules.sys.entity.SysUserEntity
import io.dj.modules.sys.service.SysDeptService
import io.dj.modules.sys.service.SysUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.util.ArrayList

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/10
 * 人员信息同步
 **/
@Service("dapUserTaskService")
open class DapUserTaskServiceImpl : DapUserTaskService {

    @Autowired
    private val sysUserService: SysUserService? = null
    @Autowired
    private val sysDeptService: SysDeptService? = null

    override fun updateDapUser() {
        val dapUsers = getDapUsers()
        dapUsers.indices.
                filter {
                    "0" == dapUsers[it].delFlag
                }
                .forEach {
                    val map = object : HashMap<String, String>() {
                        init {
                            put("dapId", dapUsers[it].id!!)
                        }
                    }
                    val users = sysUserService!!.queryList(map as Map<String, Any>?)
                    if (users.size > 0) {
                        saveSysUser(setSysUser(users[0], dapUsers[it]))
                    } else {
                        saveSysUser(setSysUser(SysUserEntity(), dapUsers[it]))
                    }
                }
        dapUsers.indices.
                filter {
                    "1" == dapUsers[it].delFlag
                }
                .forEach {
                    val map = object : HashMap<String, String>() {
                        init {
                            put("dapId", dapUsers[it].id!!)
                        }
                    }
                    val users = sysUserService!!.queryList(map as Map<String, Any>?)
                    if (users.size > 0) {
                        sysUserService.delete(users[it].id)
                    }
                }
    }

    private fun setSysUser(user: SysUserEntity, dapUser: DapUserBean): SysUserEntity {
        user.name = dapUser.name
        user.loginName = dapUser.loginName
        user.password = dapUser.password
        val map = object : HashMap<String, String>() {
            init {
                put("dapId", dapUser.deptId!!)
            }
        }
        val depts = sysDeptService!!.queryList(map as Map<String, Any>?)
        if(depts.size>0){
            user.deptId = depts[0].id
        }else{
            user.deptId = 0
        }
        user.no = dapUser.no
        user.dapId = dapUser.id
        user.hold = 1
        user.createBy = 1L
        user.updateBy = 1L
        user.email = ""
        user.mobile = dapUser.mobile
        user.status = 0
        return user
    }

    private fun saveSysUser(user: SysUserEntity) {
        if (null != user.id && !"".equals(user.id)) {
            sysUserService!!.update(user)
        } else {
            sysUserService!!.save(user)
        }
    }

    /**
     * 查询dap里面user
     */
    private fun getDapUsers(): ArrayList<DapUserBean> {
        val con = JdbcConnectUtil().dapConnect
        var ptst: PreparedStatement? = null
        var rs: ResultSet? = null
        val sql = "SELECT mobile,id,name,login_name,no,password,office_id,del_flag from sys_user where login_name != ('admin' && 'thinkgem')"
        ptst = con!!.prepareStatement(sql)
        rs = ptst.executeQuery()
        val uerBeans = ArrayList<DapUserBean>()
        while (rs.next()) {
            val uerBean = DapUserBean()
            uerBean.id = rs.getObject("id")?.toString()
            uerBean.name = rs.getObject("name")?.toString()
            uerBean.loginName = rs.getObject("login_name")?.toString()
            uerBean.no = rs.getObject("no")?.toString()
            uerBean.password = rs.getObject("password")?.toString()
            uerBean.deptId = rs.getObject("office_id")?.toString()
            uerBean.delFlag = rs.getObject("del_flag")?.toString()
            uerBean.mobile = rs.getObject("mobile")?.toString()
            uerBeans.add(uerBean)
        }
        rs.close()
        ptst.close()
        con.close()
        return uerBeans
    }
}