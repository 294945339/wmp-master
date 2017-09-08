package io.dj.modules.goods.domain;

import io.dj.common.base.domain.BaseDomain;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/4
 * 字典类别类别对应部位(无值)
 **/
public class GoodsToDictDomain extends BaseDomain {

    private GoodsTypeDomain goodsType;      //商品类别
    private Long[] clothingDictIds;

    //展示使用
    private List<ClothingDictDomain> clothingDicts;      //身体部位
    private String crmName;

    public String getCrmName() {
        return crmName;
    }

    public void setCrmName(String crmName) {
        this.crmName = crmName;
    }

    public GoodsTypeDomain getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsTypeDomain goodsType) {
        this.goodsType = goodsType;
    }

    public Long[] getClothingDictIds() {
        return clothingDictIds;
    }

    public void setClothingDictIds(Long[] clothingDictIds) {
        this.clothingDictIds = clothingDictIds;
    }

    public List<ClothingDictDomain> getClothingDicts() {
        return clothingDicts;
    }

    public void setClothingDicts(List<ClothingDictDomain> clothingDicts) {
        this.clothingDicts = clothingDicts;
    }
}
