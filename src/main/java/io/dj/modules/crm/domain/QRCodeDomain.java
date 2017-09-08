package io.dj.modules.crm.domain;

import io.dj.common.base.domain.BaseDomain;
import io.dj.modules.goods.domain.GoodsDomain;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/2
 * 二维码实体类
 **/
public class QRCodeDomain extends BaseDomain{

    private GoodsDomain goods;        //货品id
    private CustomerDomain customer;//客户id

    public GoodsDomain getGoods() {
        return goods;
    }

    public void setGoods(GoodsDomain goods) {
        this.goods = goods;
    }

    public CustomerDomain getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDomain customer) {
        this.customer = customer;
    }
}
