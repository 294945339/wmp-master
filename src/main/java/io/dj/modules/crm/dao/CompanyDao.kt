package io.dj.modules.crm.dao

import io.dj.modules.crm.domain.CompanyDomain
import io.dj.modules.sys.dao.BaseDao
import org.apache.ibatis.annotations.Mapper


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/15
 * Time: 11:27
 * Email: 294945339@qq.com
 */

@Mapper
open interface CompanyDao : BaseDao<CompanyDomain> {
}