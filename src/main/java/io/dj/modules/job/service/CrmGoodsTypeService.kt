package io.dj.modules.job.service

import io.dj.modules.goods.domain.GoodsTypeDomain
import io.dj.modules.job.bean.CrmGoodsTypeBean
import java.util.ArrayList

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * email 294945339@qq.com
 * Date: 2017/8/11
 * Description:
 **/

interface CrmGoodsTypeService {
    fun updateCrmGoodsType()
    fun getCrmGoodsType(id: String): ArrayList<CrmGoodsTypeBean>
    fun setSave(domain: GoodsTypeDomain, bean: CrmGoodsTypeBean,isEq: Boolean)
}