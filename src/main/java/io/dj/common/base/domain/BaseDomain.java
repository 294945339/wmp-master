package io.dj.common.base.domain;

import io.dj.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/7/25
 **/
public class BaseDomain implements Serializable {
    /**
     * id,自增
     */
    private Long id;

    /**
     * 状态  0：正常   1：禁用
     */
    private Integer status = 0;

    /**
     * 创建者ID
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private Date createTime = new Date();

    /**
     * 修改者ID
     */
    private Long updateBy;

    /**
     * 修改时间
     */
    private Date updateTime = new Date();

    /**
     * 备注
     */
    private String remarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 插入之前执行方法，需要手动调用
     */
    public void preInsert(Long userId) {
        if (null == userId) {
            SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
            this.createBy = user.getId();
        }
        this.status = 0;
        this.createTime = new Date();
    }

    /**
     * 更新之前执行方法，需要手动调用
     */
    public void preUpdate(Long userId) {
        if (null == userId) {
            SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
            this.updateBy = user.getId();
        }
        this.updateTime = new Date();
    }

}
