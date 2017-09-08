package io.dj.modules.sys.entity;

import io.dj.common.base.domain.BaseDomain;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/14
 * Time: 14:48
 * Email: 294945339@qq.com
 */

public class SysWeatherDomain extends BaseDomain{
    private Date today;
    private Boolean rain;

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public Boolean getRain() {
        return rain;
    }

    public void setRain(Boolean rain) {
        this.rain = rain;
    }
}
