package io.dj.modules.measure.domain;

import io.dj.common.base.domain.BaseDomain;
import io.dj.common.base.enums.TaskStatusEnum;
import io.dj.modules.crm.domain.CompanyDomain;
import io.dj.modules.crm.domain.OrderDomain;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/17
 * Time: 17:22
 * Email: 294945339@qq.com
 * 量体派遣单实体类
 */

public class MeasureDispatchDomain extends BaseDomain {

    private String code;          //派遣单号
    private CompanyDomain company;//公司
    private String type;    //量体方式
    private String crmId;
    private OrderDomain order;//通知单id
    private TaskStatusEnum taskStatus;//任务状态
    private String taskTime;

    //show
    private String companyName;
    private String companyId;
    private String startTime;
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public TaskStatusEnum getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatusEnum taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public OrderDomain getOrder() {
        return order;
    }

    public void setOrder(OrderDomain order) {
        this.order = order;
    }

    public String getCrmId() {
        return crmId;
    }

    public void setCrmId(String crmId) {
        this.crmId = crmId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CompanyDomain getCompany() {
        return company;
    }

    public void setCompany(CompanyDomain company) {
        this.company = company;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
