package io.dj.modules.goods.domain;

import io.dj.common.base.domain.BaseDomain;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/10
 * 商品类别实体类
 **/
public class GoodsTypeDomain extends BaseDomain{

    private String code;
    private String crmId;
    private String crmName;
    private String unit;    //单位
    private String erpId;
    private String erpName;
    private String typeStr;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCrmId() {
        return crmId;
    }

    public void setCrmId(String crmId) {
        this.crmId = crmId;
    }

    public String getCrmName() {
        return crmName;
    }

    public void setCrmName(String crmName) {
        this.crmName = crmName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getErpId() {
        return erpId;
    }

    public void setErpId(String erpId) {
        this.erpId = erpId;
    }

    public String getErpName() {
        return erpName;
    }

    public void setErpName(String erpName) {
        this.erpName = erpName;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

}
