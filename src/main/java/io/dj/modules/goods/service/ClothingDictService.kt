package io.dj.modules.goods.service

import io.dj.common.base.bean.BaseBean
import io.dj.common.base.service.BaseService
import io.dj.modules.goods.domain.ClothingDictDomain

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/1
 **/
interface ClothingDictService: BaseService<ClothingDictDomain>{

    /**
     * 查询类型
     */
    fun getTypeList(): ArrayList<BaseBean>
    
}