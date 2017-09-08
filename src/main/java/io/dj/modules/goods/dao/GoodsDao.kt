package io.dj.modules.goods.dao

import io.dj.modules.goods.domain.GoodsDomain
import io.dj.modules.sys.dao.BaseDao
import org.apache.ibatis.annotations.Mapper


/**
 * @author  dj
 * @email 294945339@qq.com
 * @create 2017-08-24 11:05
 * @desc      商品dao
 **/

@Mapper
interface GoodsDao: BaseDao<GoodsDomain> {
}