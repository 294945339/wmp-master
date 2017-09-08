package io.dj.modules.goods.domain;

import io.dj.common.base.domain.BaseDomain;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/7/29
 * 版型库
 **/
public class VersionLibraryDomain extends BaseDomain{

    private SpecificationSheetDomain specificationSheet;    //规格表
    private String code;                //版型编码
    private String oldCode;             //原版型编号
    private String type;                //类别
    private String crmId;

    public SpecificationSheetDomain getSpecificationSheet() {
        return specificationSheet;
    }

    public void setSpecificationSheet(SpecificationSheetDomain specificationSheet) {
        this.specificationSheet = specificationSheet;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOldCode() {
        return oldCode;
    }

    public void setOldCode(String oldCode) {
        this.oldCode = oldCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCrmId() {
        return crmId;
    }

    public void setCrmId(String crmId) {
        this.crmId = crmId;
    }
}
