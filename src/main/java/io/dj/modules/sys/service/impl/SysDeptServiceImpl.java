package io.dj.modules.sys.service.impl;

import com.qiniu.util.StringUtils;
import io.dj.modules.sys.entity.SysDeptEntity;
import io.dj.modules.sys.entity.SysUserEntity;
import io.dj.modules.sys.service.SysDeptService;
import io.dj.modules.sys.dao.SysDeptDao;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("sysDeptService")
public class SysDeptServiceImpl implements SysDeptService {
    @Autowired
    private SysDeptDao sysDeptDao;

    @Override
    public SysDeptEntity queryObject(Long deptId) {
        return sysDeptDao.queryObject(deptId);
    }

    @Override
    public List<SysDeptEntity> queryList(Map<String, Object> map) {
        return sysDeptDao.queryList(map);
    }

    @Override
    public void save(SysDeptEntity sysDept) {
        sysDept.setCreateTime(new Date());
        sysDept.setStatus(0);
        if (sysDept.getCreateBy() == null) {
            sysDept.setCreateBy(((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getId());
        }
        List<String> listIds = sysDept.getListIds();
        sysDeptDao.save(sysDept);
    }

    @Override
    public void update(SysDeptEntity sysDept) {
        if (sysDept.getUpdateBy() == null) {
            sysDept.setUpdateBy(((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getId());
        }
        sysDept.setUpdateTime(new Date());
        sysDeptDao.update(sysDept);
    }

    @Override
    public void delete(Long deptId) {
        sysDeptDao.delete(deptId);
    }

    @Override
    public List<Long> queryDetpIdList(Long parentId) {
        return sysDeptDao.queryDetpIdList(parentId);
    }

    @Override
    public String getSubDeptIdList(Long deptId) {
        //部门及子部门ID列表
        List<Long> deptIdList = new ArrayList<>();

        //获取子部门ID
        List<Long> subIdList = queryDetpIdList(deptId);
        getDeptTreeList(subIdList, deptIdList);

        //添加本部门
        deptIdList.add(deptId);

        String deptFilter = StringUtils.join(deptIdList, ",");
        return deptFilter;
    }

    /**
     * 递归
     */
    public void getDeptTreeList(List<Long> subIdList, List<Long> deptIdList) {
        for (Long deptId : subIdList) {
            List<Long> list = queryDetpIdList(deptId);
            if (list.size() > 0) {
                getDeptTreeList(list, deptIdList);
            }

            deptIdList.add(deptId);
        }
    }
}
