package io.dj.modules.goods.domain;

import io.dj.common.base.domain.BaseDomain;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * email 294945339@qq.com
 * Date: 2017/8/11
 * Description:商品目录
 **/

public class GoodsCatalogDomain extends BaseDomain{

    private String code;   //商品代码
    private String name;    //商品名称
    private String unit;    //商品单位
    private String type;    //大类
    private String typeName;//大类名称
    private String erpId;   //erpId

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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getErpId() {
        return erpId;
    }

    public void setErpId(String erpId) {
        this.erpId = erpId;
    }
}