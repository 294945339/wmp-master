package io.dj.common.base.controller;

import io.dj.modules.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * Controller公共组件
 * 
 * @author dj
 * @email 294945339@qq.com
 * @date 2017年7月9日 下午9:42:26
 */
public abstract class BaseController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected SysUserEntity getUser() {
		return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
	}

	protected Long getUserId() {
		return getUser().getId();
	}

	protected Long getDeptId() {
		return getUser().getDeptId();
	}
}
