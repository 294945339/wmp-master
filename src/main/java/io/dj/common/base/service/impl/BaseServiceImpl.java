package io.dj.common.base.service.impl;

import io.dj.common.base.domain.BaseDomain;
import io.dj.modules.sys.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/1
 **/
public class BaseServiceImpl<D extends BaseDao<T>, T extends BaseDomain> {
    /**
     * 持久层对象
     */
    @Autowired
    protected D dao;

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public T queryObject(String id) {
        return dao.queryObject(id);
    }

    /**
     * 获取单条数据
     * @param domain
     * @return
     */
    public T queryObject(T domain) {
        return dao.queryObject(domain.getId());
    }

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public T queryObject(Long id) {
        return dao.queryObject(id);
    }

    /**
     * 查询列表数据
     * @param map
     * @return
     */
    public List<T> queryList(Map<String, Object> map) {
        return dao.queryList(map);
    }

    /**
     * 保存数据（插入或更新）
     * @param domain
     */
    public void save(T domain) {
        if (domain.getId() == null || "".equals(domain.getId())){
            domain.preInsert(domain.getCreateBy());
            dao.save(domain);
        }else{
            domain.preUpdate(domain.getUpdateBy());
            dao.update(domain);
        }
    }

    /**
     * 删除数据
     * @param domain
     */
    public void delete(T domain) {
        dao.delete(domain.getId());
    }

    /**
     * 删除数据
     * @param id
     */
    public void delete(Long id) {
        dao.delete(id);
    }


    public int queryTotal(Map<String, Object> map){
       return dao.queryTotal(map);
    }
}
