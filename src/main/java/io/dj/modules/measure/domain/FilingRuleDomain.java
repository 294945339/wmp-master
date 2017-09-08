package io.dj.modules.measure.domain;

import io.dj.common.base.domain.BaseDomain;
import io.dj.modules.goods.domain.ClothingDictDomain;
import io.dj.modules.goods.domain.GoodsToDictDomain;
import io.dj.modules.goods.domain.GoodsTypeDomain;

/**
 * @author dj
 * @email 294945339@qq.com
 * @create 2017-08-28 11:17
 * @desc 归档规则实体类
 **/

public class FilingRuleDomain extends BaseDomain {

    private GoodsTypeDomain goodsType;      //产品类别
    private ClothingDictDomain first;        //第1优先
    private ClothingDictDomain second;       //第2优先
    private ClothingDictDomain third;        //第3优先
    private ClothingDictDomain fourth;       //第4优先

    public GoodsTypeDomain getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsTypeDomain goodsType) {
        this.goodsType = goodsType;
    }

    public ClothingDictDomain getFirst() {
        return first;
    }

    public void setFirst(ClothingDictDomain first) {
        this.first = first;
    }

    public ClothingDictDomain getSecond() {
        return second;
    }

    public void setSecond(ClothingDictDomain second) {
        this.second = second;
    }

    public ClothingDictDomain getThird() {
        return third;
    }

    public void setThird(ClothingDictDomain third) {
        this.third = third;
    }

    public ClothingDictDomain getFourth() {
        return fourth;
    }

    public void setFourth(ClothingDictDomain fourth) {
        this.fourth = fourth;
    }
}
