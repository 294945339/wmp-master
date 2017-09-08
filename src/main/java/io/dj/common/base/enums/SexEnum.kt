package io.dj.common.base.enums


enum class SexEnum(chName: String, index: Int) {

    man("男",0),        //男
    woman("女",1),      //女
    neutral("通用",2);    //通用

    private var chName: String? = null
    private var index: Int? = null

    fun getChName(): String? {
        return chName
    }

    fun setChName(chName: String) {
        this.chName = chName
    }

    fun getIndex(): Int? {
        return index
    }

    fun setIndex(index: Int) {
        this.index = index
    }

}