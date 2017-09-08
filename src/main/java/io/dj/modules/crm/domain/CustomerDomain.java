package io.dj.modules.crm.domain;

import io.dj.common.base.domain.BaseDomain;
import io.dj.common.base.enums.SexEnum;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/7/29
 * 客户
 **/
public class CustomerDomain extends BaseDomain {

    private String name;            //客户名称
    private SexEnum sex = SexEnum.man;            //性别
    private CompanyDomain company;           //所属企业
    private String depName;         //部门
    private String crmId;

    public String getCrmId() {
        return crmId;
    }

    public void setCrmId(String crmId) {
        this.crmId = crmId;
    }

    public CompanyDomain getCompany() {
        return company;
    }

    public void setCompany(CompanyDomain company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }
}
