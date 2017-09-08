package io.dj.modules.goods.service.impl

import io.dj.common.base.service.impl.BaseServiceImpl
import io.dj.modules.goods.dao.VersionLibraryDao
import io.dj.modules.goods.domain.VersionLibraryDomain
import io.dj.modules.goods.service.VersionLibraryService
import org.springframework.stereotype.Service


/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/17
 * Time: 17:05
 * Email: 294945339@qq.com
 */

@Service("versionLibraryService")
open class VersionLibraryServiceImpl: BaseServiceImpl<VersionLibraryDao,VersionLibraryDomain>(),VersionLibraryService {
}