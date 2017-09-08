package io.dj.modules.goods.dao

import io.dj.modules.goods.domain.VersionLibraryDomain
import io.dj.modules.sys.dao.BaseDao
import org.apache.ibatis.annotations.Mapper


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/17
 * Time: 16:31
 * Email: 294945339@qq.com
 */

@Mapper
open interface VersionLibraryDao:BaseDao<VersionLibraryDomain> {
}