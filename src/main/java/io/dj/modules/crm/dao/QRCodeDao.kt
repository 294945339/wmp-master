package io.dj.modules.crm.dao

import io.dj.modules.crm.domain.QRCodeDomain
import io.dj.modules.sys.dao.BaseDao
import org.apache.ibatis.annotations.Mapper


/**
 * @author  dj
 * @email 294945339@qq.com
 * @create 2017-08-24 11:10
 * @desc      商品对应客户,并且有二维码
 **/

@Mapper
interface QRCodeDao: BaseDao<QRCodeDomain> {
}