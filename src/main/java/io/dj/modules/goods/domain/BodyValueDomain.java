package io.dj.modules.goods.domain;

import io.dj.common.base.domain.BaseDomain;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/7/29
 * 部位和数值
 **/
public class BodyValueDomain extends BaseDomain{

    private ClothingDictDomain clothingDict;              //字典部位id
    private String numericalValue;          //数值

    public ClothingDictDomain getClothingDict() {
        return clothingDict;
    }

    public void setClothingDict(ClothingDictDomain clothingDict) {
        this.clothingDict = clothingDict;
    }

    public String getNumericalValue() {
        return numericalValue;
    }

    public void setNumericalValue(String numericalValue) {
        this.numericalValue = numericalValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BodyValueDomain)) return false;

        BodyValueDomain that = (BodyValueDomain) o;

        if (clothingDict != null ? !clothingDict.equals(that.clothingDict) : that.clothingDict != null) return false;
        return numericalValue != null ? numericalValue.equals(that.numericalValue) : that.numericalValue == null;
    }

    @Override
    public int hashCode() {
        int result = clothingDict != null ? clothingDict.hashCode() : 0;
        result = 31 * result + (numericalValue != null ? numericalValue.hashCode() : 0);
        return result;
    }
}
