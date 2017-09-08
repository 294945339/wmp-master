package io.dj.modules.measure.domain;

import io.dj.common.base.domain.BaseDomain;
import io.dj.modules.goods.domain.BodyValueDomain;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * email 294945339@qq.com
 * Date: 2017/8/26
 * Description:着装特体
 **/

public class ClothingSpecialBodyDomain extends BaseDomain {

    private ClothingHabitDomain clothingHabit;//着装习惯
    private String name;
    private Long[] bodyValueIds;

    //Show
    private List<BodyValueDomain> bodyValues;

    public ClothingHabitDomain getClothingHabit() {
        return clothingHabit;
    }

    public void setClothingHabit(ClothingHabitDomain clothingHabit) {
        this.clothingHabit = clothingHabit;
    }

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