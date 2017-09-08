package io.dj.modules.goods.dao

import io.dj.modules.goods.domain.SpecificationSheetDomain
import io.dj.modules.sys.dao.BaseDao
import org.apache.ibatis.annotations.Mapper


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/15
 * Time: 14:43
 * Email: 294945339@qq.com
 */
@Mapper
open interface SpecificationSheetDao: BaseDao<SpecificationSheetDomain> {
}