package io.dj.modules.goods.dao

import io.dj.modules.goods.domain.BodyValueDomain
import io.dj.modules.sys.dao.BaseDao
import org.apache.ibatis.annotations.Mapper

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/1
 **/

@Mapper
open interface BodyValueDao : BaseDao<BodyValueDomain> {
}