package io.dj.modules.goods.domain;

import io.dj.common.base.domain.BaseDomain;
import io.dj.common.base.enums.SexEnum;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/7/31
 * 服装字典
 **/
public class ClothingDictDomain extends BaseDomain{

    private String name;                //名字
    private String code;                //代码
    private String typeName;            //类型名称
    private String type;                //类型
    private int sort = 0;               //排序
    private Boolean canUpdate = true;   //能否修改
    private int priority;               //优先级
    private SexEnum sex = SexEnum.man;  //性别

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Boolean getCanUpdate() {
        return canUpdate;
    }

    public void setCanUpdate(Boolean canUpdate) {
        this.canUpdate = canUpdate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
