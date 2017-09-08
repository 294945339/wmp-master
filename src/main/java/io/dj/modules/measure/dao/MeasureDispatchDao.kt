package io.dj.modules.measure.dao

import io.dj.modules.measure.domain.MeasureDispatchDomain
import io.dj.modules.sys.dao.BaseDao
import org.apache.ibatis.annotations.Mapper


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/18
 * Time: 8:33
 * Email: 294945339@qq.com
 */
@Mapper
open interface MeasureDispatchDao:BaseDao<MeasureDispatchDomain> {
}