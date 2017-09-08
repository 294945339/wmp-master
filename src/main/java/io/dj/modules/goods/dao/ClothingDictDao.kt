package io.dj.modules.goods.dao

import io.dj.modules.goods.domain.ClothingDictDomain
import io.dj.modules.sys.dao.BaseDao
import org.apache.ibatis.annotations.Mapper

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/1
 **/

@Mapper
open interface ClothingDictDao : BaseDao<ClothingDictDomain> {
    fun getTypeList(): ArrayList<String>
}