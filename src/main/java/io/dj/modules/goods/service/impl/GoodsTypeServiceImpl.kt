package io.dj.modules.goods.service.impl

import io.dj.common.base.service.impl.BaseServiceImpl
import io.dj.modules.goods.dao.GoodsTypeDao
import io.dj.modules.goods.domain.GoodsTypeDomain
import io.dj.modules.goods.service.GoodsTypeService
import org.springframework.stereotype.Service

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/10
 **/
@Service("goodsTypeService")
open class GoodsTypeServiceImpl: BaseServiceImpl<GoodsTypeDao, GoodsTypeDomain>(),GoodsTypeService {

}