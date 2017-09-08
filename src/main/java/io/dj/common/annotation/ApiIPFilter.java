package io.dj.common.annotation;

import java.lang.annotation.*;

/**
 * apiIP过滤注解
 * 过滤外网ip
 * @author dj
 * @email 294945339@qq.com
 * @date 2017年8月1日 上午10:19:56
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiIPFilter {

}
