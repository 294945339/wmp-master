package io.dj.modules.goods.domain;

import io.dj.common.base.domain.BaseDomain;
import io.dj.common.base.enums.SexEnum;
import io.dj.modules.goods.enums.SpecificationSheetTypeEnum;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/7/29
 * 规格表
 **/
public class SpecificationSheetDomain extends BaseDomain{

    private String crmId;
    private String code;            //规格表代码
    private String name;           //规格表名称
    private SexEnum sex = SexEnum.man;            //男女
    private SpecificationSheetTypeEnum type;            //类别
    private String versionName;      //版本名称
    private Boolean canUse = true;         //是否使用
    private String fileCode;            //文件编码

    //show
    private List<SpecificationSheetRelationDomain> specificationSheetRelations;

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

    public SpecificationSheetTypeEnum getType() {
        return type;
    }

    public void setType(SpecificationSheetTypeEnum type) {
        this.type = type;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Boolean getCanUse() {
        return canUse;
    }

    public void setCanUse(Boolean canUse) {
        this.canUse = canUse;
    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public List<SpecificationSheetRelationDomain> getSpecificationSheetRelations() {
        return specificationSheetRelations;
    }

    public void setSpecificationSheetRelations(List<SpecificationSheetRelationDomain> specificationSheetRelations) {
        this.specificationSheetRelations = specificationSheetRelations;
    }
}
