package io.dj.modules.goods.domain;

import io.dj.common.base.domain.BaseDomain;
import io.dj.modules.goods.domain.GoodsCatalogDomain;
import io.dj.modules.goods.domain.GoodsTypeDomain;

/**
 * @author dj
 * @email 294945339@qq.com
 * @create 2017-08-24 10:39
 * @desc 商品实体类
 **/

public class GoodsDomain extends BaseDomain{

    //量体通知的时候拿到商品类别
    private GoodsTypeDomain goodsType;          //商品类别
    private GoodsCatalogDomain goodsCatalog;    //商品目录

    public GoodsTypeDomain getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsTypeDomain goodsType) {
        this.goodsType = goodsType;
    }

    public GoodsCatalogDomain getGoodsCatalog() {
        return goodsCatalog;
    }

    public void setGoodsCatalog(GoodsCatalogDomain goodsCatalog) {
        this.goodsCatalog = goodsCatalog;
    }
}
