package io.dj.modules.goods.service.impl

import io.dj.common.base.bean.BaseBean
import io.dj.common.base.service.impl.BaseServiceImpl
import io.dj.modules.goods.dao.GoodsCatalogDao
import io.dj.modules.goods.domain.GoodsCatalogDomain
import io.dj.modules.goods.service.GoodsCatalogService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * email 294945339@qq.com
 * Date: 2017/8/11
 * Description:
 **/
@Service("goodsCatalogService")
open class GoodsCatalogServiceIpml : BaseServiceImpl<GoodsCatalogDao, GoodsCatalogDomain>(), GoodsCatalogService {

    @Autowired private val goodsCatalogDao: GoodsCatalogDao? = null

    override fun findNameList(): ArrayList<BaseBean> {
        var beans = ArrayList<BaseBean>()
        goodsCatalogDao!!.findNameList().forEach {
            name ->
            beans.add(object : BaseBean() {
                init {
                    value = name
                    key = name
                }
            })
        }
        return beans
    }
}