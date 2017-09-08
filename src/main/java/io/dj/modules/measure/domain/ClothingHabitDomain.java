package io.dj.modules.measure.domain;

import io.dj.common.base.domain.BaseDomain;
import io.dj.modules.goods.domain.BodyValueDomain;

import java.util.List;

/**
 * @author dj
 * @email 294945339@qq.com
 * @create 2017-08-25 9:19
 * @desc 着装习惯实体类
 **/

public class ClothingHabitDomain extends BaseDomain {

    private String name;
    private Long[] bodyValueIds;

    private List<BodyValueDomain> bodyValues;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long[] getBodyValueIds() {
        return bodyValueIds;
    }

    public void setBodyValueIds(Long[] bodyValueIds) {
        this.bodyValueIds = bodyValueIds;
    }

    public List<BodyValueDomain> getBodyValues() {
        return bodyValues;
    }

    public void setBodyValues(List<BodyValueDomain> bodyValues) {
        this.bodyValues = bodyValues;
    }
}
