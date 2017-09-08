package io.dj.modules.goods.enums;

import io.dj.common.base.enums.SexEnum;
/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/17
 * Time: 10:35
 * Email: 294945339@qq.com
 */

public enum SpecificationSheetTypeEnum {

    manJacket("男上衣",SexEnum.man),
    manOvercoat("男大衣",SexEnum.man),
    manVest("男马甲",SexEnum.man),
    manPants("男西裤",SexEnum.man),
    womanJacket("女上衣",SexEnum.woman),
    womanOvercoat("女大衣",SexEnum.woman),
    womanVest("女马甲",SexEnum.woman),
    womanPants("女西裤",SexEnum.woman),
    womanSkirt("女裙",SexEnum.woman);

    private String name ;
    private SexEnum sex ;

    SpecificationSheetTypeEnum(String name, SexEnum sex) {
        this.name = name ;
        this.sex = sex ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }
}
