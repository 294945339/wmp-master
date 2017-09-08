package io.dj.modules.sys.entity;

import io.dj.common.base.domain.BaseDomain;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/18
 * Time: 9:18
 * Email: 294945339@qq.com
 */

public class ChAreaDomain extends BaseDomain {

    private String parent_id;
    private String name;
    private Integer sort;

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
