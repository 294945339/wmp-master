package io.dj.modules.sys.dao

import io.dj.modules.sys.entity.SysWeatherDomain
import org.apache.ibatis.annotations.Mapper


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/14
 * Time: 14:58
 * Email: 294945339@qq.com
 */
@Mapper
open interface SysWeatherDao : BaseDao<SysWeatherDomain> {
}