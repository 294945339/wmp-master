package io.dj.modules.goods.service.impl

import io.dj.common.base.service.impl.BaseServiceImpl
import io.dj.common.base.bean.BaseBean
import io.dj.modules.goods.dao.ClothingDictDao
import io.dj.modules.goods.domain.ClothingDictDomain
import io.dj.modules.goods.service.ClothingDictService
import org.springframework.stereotype.Service

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/1
 **/

@Service("clothingDictService")
open class ClothingDictServiceImpl : BaseServiceImpl<ClothingDictDao, ClothingDictDomain>(), ClothingDictService {

    override fun getTypeList(): ArrayList<BaseBean> {
        var list = object : ArrayList<BaseBean>() {
            init {
                add(
                        object : BaseBean() {
                            init {
                                key = "全部"
                                value = ""
                            }
                        }
                )
                dao.getTypeList().forEach {
                    str ->
                    add(
                            object : BaseBean() {
                                init {
                                    key = str
                                    value = str
                                }
                            }
                    )
                }
            }
        }
        return list
    }

}