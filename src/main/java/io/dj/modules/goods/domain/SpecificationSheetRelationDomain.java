package io.dj.modules.goods.domain;

import io.dj.common.base.domain.BaseDomain;
import io.dj.common.base.enums.SexEnum;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/7/29
 * 规格表子表
 **/
public class SpecificationSheetRelationDomain extends BaseDomain{

    private String code ;           //规格表代码
    private String name;            //号
    private String type;            //型
    private String size;            //规格
    private String crmId;
    private Integer sort;
    private Long specificationSheet;//父表
    private Long[] bodyValueIds;

    //展示
    private List<BodyValueDomain> bodyValues;
    private String fartherId;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Long[] getBodyValueIds() {
        return bodyValueIds;
    }

    public void setBodyValueIds(Long[] bodyValueIds) {
        this.bodyValueIds = bodyValueIds;
    }

    public List<BodyValueDomain> getBodyValues() {
        return bodyValues;
    }

    public void setBodyValues(List<BodyValueDomain> bodyValues) {
        this.bodyValues = bodyValues;
    }

    public String getCrmId() {
        return crmId;
    }

    public void setCrmId(String crmId) {
        this.crmId = crmId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }


    public Long getSpecificationSheet() {
        return specificationSheet;
    }

    public void setSpecificationSheet(Long specificationSheet) {
        this.specificationSheet = specificationSheet;
    }

    public String getFartherId() {
        return fartherId;
    }

    public void setFartherId(String fartherId) {
        this.fartherId = fartherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpecificationSheetRelationDomain)) return false;

        SpecificationSheetRelationDomain that = (SpecificationSheetRelationDomain) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;
        return sort != null ? sort.equals(that.sort) : that.sort == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (sort != null ? sort.hashCode() : 0);
        return result;
    }
}
