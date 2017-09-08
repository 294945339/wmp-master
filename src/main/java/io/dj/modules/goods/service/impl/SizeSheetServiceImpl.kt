package io.dj.modules.goods.service.impl

import io.dj.common.base.service.impl.BaseServiceImpl
import io.dj.modules.goods.dao.SizeSheetDao
import io.dj.modules.goods.domain.SizeSheetDomain
import io.dj.modules.goods.service.SizeSheetService
import org.springframework.stereotype.Service

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * email 294945339@qq.com
 * Date: 2017/8/11
 * Description:
 **/
@Service("sizeSheetService")
open class SizeSheetServiceImpl:BaseServiceImpl<SizeSheetDao,SizeSheetDomain>(),SizeSheetService {
}