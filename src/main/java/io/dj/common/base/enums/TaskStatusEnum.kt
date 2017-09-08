package io.dj.common.base.enums

enum class TaskStatusEnum(var chName : String, var num : Int) {

    Do("草稿", 0),
    Doing("进行", 1),
    Done("完成", 2),
    Out("退回", 3),
    Other("其他",4);

    fun getChName(i: Int): String? {
        return TaskStatusEnum.values()
                .firstOrNull { it.num == i }
                ?.chName
    }

    fun toChName(): String {
        return this.chName ?: this.chName
    }

    fun toNum(): Int {
        return if (this.num == null) this.ordinal else this.num
    }

}