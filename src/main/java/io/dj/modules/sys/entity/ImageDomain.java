package io.dj.modules.sys.entity;

import io.dj.common.base.domain.BaseDomain;

/**
 * @author dj
 * @email 294945339@qq.com
 * @create 2017-08-25 11:01
 * @desc 图片base64转码实体类
 **/

public class ImageDomain extends BaseDomain{
    private String code;
    private int sort;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
