package io.dj.common.base.service;

import io.dj.common.base.domain.BaseDomain;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/16
 * Time: 14:27
 * Email: 294945339@qq.com
 */

public interface BaseService <T extends BaseDomain>  {

    T queryObject(Long id);

    int queryTotal(Map<String, Object> map);

    List<T> queryList(Map<String, Object> map);

    void save(T domain);

    void delete(Long id);
}
