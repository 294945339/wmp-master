package io.dj.modules.measure.domain;

import io.dj.common.base.domain.BaseDomain;
import io.dj.common.base.enums.SexEnum;
import io.dj.modules.goods.domain.ClothingDictDomain;
import io.dj.modules.measure.Enums.AuditingTypeEnum;

/**
 * @author dj
 * @email 294945339@qq.com
 * @create 2017-08-29 8:37
 * @desc 审单规则实体类
 **/

public class AuditingDomain extends BaseDomain{

    private ClothingDictDomain clothingDict;    //部位
    private int min;                            //最小
    private int max;                            //最大
    private SexEnum sex;                        //性别
    private AuditingTypeEnum type;              //审单类型

    public ClothingDictDomain getClothingDict() {
        return clothingDict;
    }

    public void setClothingDict(ClothingDictDomain clothingDict) {
        this.clothingDict = clothingDict;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public AuditingTypeEnum getType() {
        return type;
    }

    public void setType(AuditingTypeEnum type) {
        this.type = type;
    }
}
