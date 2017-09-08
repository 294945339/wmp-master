package io.dj.modules.goods.service.impl

import io.dj.common.base.service.impl.BaseServiceImpl
import io.dj.modules.goods.dao.BodyValueDao
import io.dj.modules.goods.domain.BodyValueDomain
import io.dj.modules.goods.service.BodyValueService
import org.springframework.stereotype.Service

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/1
 **/

@Service("bodyValueService")
open class BodyValueServiceImpl : BaseServiceImpl<BodyValueDao, BodyValueDomain>(), BodyValueService {

}