package io.dj.modules.measure.domain

import io.dj.modules.api.entity.UserEntity
import io.dj.common.base.enums.TaskStatusEnum
import io.dj.common.base.domain.BaseDomain

/**
 * 量体任务实体类
 */
open class VolumeTaskDomain : BaseDomain() {

    /**
     * 任务名称
     */
    private var name : String ? = null

    /**
     * crm对应的id
     */
    private var crmId : String ? = null

    /**
     * 任务状态
     */
    private var taskStatus : TaskStatusEnum? = null

    /**
     * 分配的量体师
     */
    private var users : List<UserEntity>? = null
}