package io.dj.modules.sys.dao

import io.dj.modules.sys.entity.ImageDomain
import org.apache.ibatis.annotations.Mapper


/**
 * @author  dj
 * @email 294945339@qq.com
 * @create 2017-08-25 11:05
 * @desc
 **/

@Mapper
interface ImageDao: BaseDao<ImageDomain> {
}