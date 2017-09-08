package io.dj.modules.sys.service.impl;

import com.xiaoleilu.hutool.util.CollectionUtil;
import io.dj.common.utils.PasswordUtils;
import io.dj.modules.sys.dao.SysUserDao;
import io.dj.modules.sys.entity.SysUserEntity;
import io.dj.modules.sys.service.SysRoleService;
import io.dj.modules.sys.service.SysUserRoleService;
import io.dj.modules.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:46:09
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysRoleService sysRoleService;

	@Override
	public List<String> queryAllPerms(Long userId) {
		return sysUserDao.queryAllPerms(userId);
	}

	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return sysUserDao.queryAllMenuId(userId);
	}

	@Override
	public SysUserEntity queryByUserName(String username) {
		return sysUserDao.queryByUserName(username);
	}

	@Override
	public SysUserEntity queryByUserLoginName(String loginName) {
		return sysUserDao.queryByUserLoginName(loginName);
	}

	@Override
	public SysUserEntity queryObject(Long userId) {
		return sysUserDao.queryObject(userId);
	}

	@Override
	public List<SysUserEntity> queryList(Map<String, Object> map){
		return sysUserDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysUserDao.queryTotal(map);
	}

	@Override
	@Transactional
	public void save(SysUserEntity user) {
		user.setCreateTime(new Date());
		if(null == user.getCreateBy()){
            user.setCreateBy(((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getId());
			if(StringUtils.isBlank(user.getPassword())){
				user.setPassword(new PasswordUtils().entryptPassword(user.getPassword()));
			}
		}
		//sha256加密
//		String salt = RandomStringUtils.randomAlphanumeric(20);
//		user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
//		user.setSalt(salt);
		sysUserDao.save(user);

        if(user.getRoleIdList() != null && user.getRoleIdList().size()>0){
            //保存用户与角色关系
            sysUserRoleService.saveOrUpdate(user.getId(), user.getRoleIdList());
        }
	}

	@Override
	@Transactional
	public void update(SysUserEntity user) {
        user.setUpdateTime(new Date());
        if(null == user.getCreateBy()) {
            user.setUpdateBy(((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getId());
            if(StringUtils.isBlank(user.getPassword())){
                user.setPassword(new PasswordUtils().entryptPassword(user.getPassword()));
            }
        }
		sysUserDao.update(user);
        if(user.getRoleIdList() != null && user.getRoleIdList().size()>0){
            //保存用户与角色关系
            sysUserRoleService.saveOrUpdate(user.getId(), user.getRoleIdList());
        }
	}

	@Override
	@Transactional
	public void delete(Long userId) {
        sysUserDao.delete(userId);
	}

	@Override
	public int updatePassword(Long userId, String password, String newPassword) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("password", password);
		map.put("newPassword", newPassword);
		return sysUserDao.updatePassword(map);
	}

}
