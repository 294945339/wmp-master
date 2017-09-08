package io.dj.modules.crm.domain;

import io.dj.common.base.domain.BaseDomain;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/7/29
 * 量体通知单
 **/
public class OrderDomain extends BaseDomain{

    private String crmId;
    private String noticeNo;            //订单通知编号
    private String originalNo;          //量体通知单号
    private String type;                //量体类型
    private String applyTime;           //申请时间
    private String salesmanName;        //业务员
    private String salesmanPhone;       //业务员联系号码
    private String contactsName;        //联系人
    private String contactsPhone;        //联系人电话
    private String manNum;              //男人数
    private String womanNum;            //女人数
    private String manConfig;           //男人配置
    private String womanConfig;         //女人配置
    private String measureType;           //量体方式
    private String packingType;           //装箱要求
    private String packType;            //打包要求
    private String measureTime;         //量体时间
    private CompanyDomain company;       //量体单位
    private String deliverTime;           //交付时间
    private Long[] OrderNameListIds;       //派遣单名单

    //show
    private List<OrderPeoplesDomain> OrderNameLists; //客户

    public String getCrmId() {
        return crmId;
    }

    public void setCrmId(String crmId) {
        this.crmId = crmId;
    }

    public String getOriginalNo() {
        return originalNo;
    }

    public void setOriginalNo(String originalNo) {
        this.originalNo = originalNo;
    }

    public String getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(String noticeNo) {
        this.noticeNo = noticeNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getMeasureTime() {
        return measureTime;
    }

    public void setMeasureTime(String measureTime) {
        this.measureTime = measureTime;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public String getSalesmanPhone() {
        return salesmanPhone;
    }

    public void setSalesmanPhone(String salesmanPhone) {
        this.salesmanPhone = salesmanPhone;
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public CompanyDomain getCompany() {
        return company;
    }

    public void setCompany(CompanyDomain company) {
        this.company = company;
    }

    public String getManNum() {
        return manNum;
    }

    public void setManNum(String manNum) {
        this.manNum = manNum;
    }

    public String getWomanNum() {
        return womanNum;
    }

    public void setWomanNum(String womanNum) {
        this.womanNum = womanNum;
    }

    public String getManConfig() {
        return manConfig;
    }

    public void setManConfig(String manConfig) {
        this.manConfig = manConfig;
    }

    public String getWomanConfig() {
        return womanConfig;
    }

    public void setWomanConfig(String womanConfig) {
        this.womanConfig = womanConfig;
    }

    public String getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(String deliverTime) {
        this.deliverTime = deliverTime;
    }

    public String getMeasureType() {
        return measureType;
    }

    public void setMeasureType(String measureType) {
        this.measureType = measureType;
    }

    public String getPackingType() {
        return packingType;
    }

    public void setPackingType(String packingType) {
        this.packingType = packingType;
    }

    public String getPackType() {
        return packType;
    }

    public void setPackType(String packType) {
        this.packType = packType;
    }

    public Long[] getOrderNameListIds() {
        return OrderNameListIds;
    }

    public void setOrderNameListIds(Long[] orderNameListIds) {
        OrderNameListIds = orderNameListIds;
    }

    public List<OrderPeoplesDomain> getOrderNameLists() {
        return OrderNameLists;
    }

    public void setOrderNameLists(List<OrderPeoplesDomain> orderNameLists) {
        OrderNameLists = orderNameLists;
    }
}
