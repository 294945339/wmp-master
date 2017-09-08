package io.dj.modules.goods.dao

import io.dj.modules.goods.domain.GoodsTypeDomain
import io.dj.modules.sys.dao.BaseDao
import org.apache.ibatis.annotations.Mapper

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/10
 **/
@Mapper
open interface GoodsTypeDao: BaseDao<GoodsTypeDomain> {
}