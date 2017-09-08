package io.dj.modules.measure.dao

import io.dj.modules.measure.domain.ClothingHabitDomain
import io.dj.modules.sys.dao.BaseDao
import org.apache.ibatis.annotations.Mapper


/**
 * @author  dj
 * @email 294945339@qq.com
 * @create 2017-08-25 9:33
 * @desc
 **/

@Mapper
interface ClothingHabitDao: BaseDao<ClothingHabitDomain> {
}