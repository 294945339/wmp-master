package io.dj.modules.sys.entity;

import io.dj.common.base.domain.BaseDomain;
import io.dj.common.validator.group.AddGroup;
import io.dj.common.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 系统用户
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:28:55
 */
public class SysUserEntity extends BaseDomain {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "用户名称", groups = AddGroup.class)
    private String name;    // 姓名
    @NotBlank(message = "登录名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String loginName;// 登录名
    private String no;        // 工号

    private String dapPassword;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = AddGroup.class)
    private String password;

    /**
     * 盐
     */
//    private String salt;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 角色ID列表
     */
    private List<Long> roleIdList;

    private Long[] roles;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 是否保留
     */
    private Integer hold;

    private String dapId;

    /**
     * 设置：密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取：密码
     *
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置：邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取：邮箱
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置：手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：手机号
     *
     * @return String
     */
    public String getMobile() {
        return mobile;
    }

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

//    public String getSalt() {
//        return salt;
//    }
//
//    public void setSalt(String salt) {
//        this.salt = salt;
//    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getHold() {
        return hold;
    }

    public void setHold(Integer hold) {
        this.hold = hold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Long[] getRoles() {
        return roles;
    }

    public void setRoles(Long[] roles) {
        this.roles = roles;
    }

    public String getDapPassword() {
        return dapPassword;
    }

    public void setDapPassword(String dapPassword) {
        this.dapPassword = dapPassword;
    }

    public String getDapId() {
        return dapId;
    }

    public void setDapId(String dapId) {
        this.dapId = dapId;
    }
}
