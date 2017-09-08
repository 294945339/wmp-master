package io.dj.modules.sys.dao

import io.dj.modules.sys.entity.ChAreaDomain
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/18
 * Time: 9:19
 * Email: 294945339@qq.com
 */
@Mapper
open interface ChAreaDao: BaseDao<ChAreaDomain> {

    @Select(" select * from sys_ch_area where id=#{id}")
    fun queryObject(id: Long)



}