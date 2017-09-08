package io.dj.modules.crm.domain;

import io.dj.common.base.domain.BaseDomain;
import io.dj.common.base.enums.SexEnum;
import io.dj.modules.goods.domain.GoodsTypeDomain;
import io.dj.modules.measure.Enums.MeasureStatusEnum;

/**
 * @author dj
 * @email 294945339@qq.com
 * @create 2017-08-24 16:13
 * @desc 量体通知单的人员名单(此处有坑)
 **/

public class OrderPeoplesDomain extends BaseDomain {

    private String name;
    private String dep;
    private String code;           //工号
    private SexEnum sex;
    private String crmId;
    private GoodsTypeDomain jacket;     //上衣
    private String jacketValue;
    private GoodsTypeDomain trousers;   //裤子
    private String trousersValue;
    private GoodsTypeDomain vest;       //马甲
    private String vestValue;
    private GoodsTypeDomain shirt;     //衬衫
    private String shirtValue;
    private GoodsTypeDomain skirt;     //裙子
    private String skirtValue;
    private GoodsTypeDomain overcoat;     //大衣
    private String overcoatValue;
    private MeasureStatusEnum taskStatus;

    public MeasureStatusEnum getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(MeasureStatusEnum taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getCrmId() {
        return crmId;
    }

    public void setCrmId(String crmId) {
        this.crmId = crmId;
    }

    public GoodsTypeDomain getJacket() {
        return jacket;
    }

    public void setJacket(GoodsTypeDomain jacket) {
        this.jacket = jacket;
    }

    public String getJacketValue() {
        return jacketValue;
    }

    public void setJacketValue(String jacketValue) {
        this.jacketValue = jacketValue;
    }

    public GoodsTypeDomain getTrousers() {
        return trousers;
    }

    public void setTrousers(GoodsTypeDomain trousers) {
        this.trousers = trousers;
    }

    public String getTrousersValue() {
        return trousersValue;
    }

    public void setTrousersValue(String trousersValue) {
        this.trousersValue = trousersValue;
    }

    public GoodsTypeDomain getVest() {
        return vest;
    }

    public void setVest(GoodsTypeDomain vest) {
        this.vest = vest;
    }

    public String getVestValue() {
        return vestValue;
    }

    public void setVestValue(String vestValue) {
        this.vestValue = vestValue;
    }

    public GoodsTypeDomain getShirt() {
        return shirt;
    }

    public void setShirt(GoodsTypeDomain shirt) {
        this.shirt = shirt;
    }

    public String getShirtValue() {
        return shirtValue;
    }

    public void setShirtValue(String shirtValue) {
        this.shirtValue = shirtValue;
    }

    public GoodsTypeDomain getSkirt() {
        return skirt;
    }

    public void setSkirt(GoodsTypeDomain skirt) {
        this.skirt = skirt;
    }

    public String getSkirtValue() {
        return skirtValue;
    }

    public void setSkirtValue(String skirtValue) {
        this.skirtValue = skirtValue;
    }

    public GoodsTypeDomain getOvercoat() {
        return overcoat;
    }

    public void setOvercoat(GoodsTypeDomain overcoat) {
        this.overcoat = overcoat;
    }

    public String getOvercoatValue() {
        return overcoatValue;
    }

    public void setOvercoatValue(String overcoatValue) {
        this.overcoatValue = overcoatValue;
    }
}
