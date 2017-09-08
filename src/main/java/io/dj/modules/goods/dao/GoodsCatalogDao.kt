package io.dj.modules.goods.dao

import io.dj.modules.goods.domain.GoodsCatalogDomain
import io.dj.modules.sys.dao.BaseDao
import org.apache.ibatis.annotations.Mapper

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * email 294945339@qq.com
 * Date: 2017/8/11
 * Description:
 **/
@Mapper
open interface GoodsCatalogDao : BaseDao<GoodsCatalogDomain> {
    fun findNameList(): List<String>
}