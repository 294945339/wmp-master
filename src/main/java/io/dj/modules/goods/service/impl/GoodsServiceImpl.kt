package io.dj.modules.goods.service.impl

import io.dj.common.base.service.impl.BaseServiceImpl
import io.dj.modules.goods.dao.GoodsDao
import io.dj.modules.goods.domain.GoodsDomain
import io.dj.modules.goods.service.GoodsService


/**
 * @author  dj
 * @email 294945339@qq.com
 * @create 2017-08-24 11:21
 * @desc      货物serviceImpl
 **/

open class GoodsServiceImpl : BaseServiceImpl<GoodsDao,GoodsDomain>(),GoodsService {
}