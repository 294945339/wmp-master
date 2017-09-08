package io.dj.modules.sys.service.impl

import io.dj.common.base.service.impl.BaseServiceImpl
import io.dj.modules.sys.dao.ImageDao
import io.dj.modules.sys.entity.ImageDomain
import io.dj.modules.sys.service.ImageService
import org.springframework.stereotype.Service


/**
 * @author  dj
 * @email 294945339@qq.com
 * @create 2017-08-25 11:14
 * @desc
 **/
@Service("imageService")
open class ImageServiceImpl:BaseServiceImpl<ImageDao,ImageDomain>(),ImageService {
}