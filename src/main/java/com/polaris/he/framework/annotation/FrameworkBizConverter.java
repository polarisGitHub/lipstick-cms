package com.polaris.he.framework.annotation;

import com.polaris.he.framework.entity.constanst.CosmeticsEnum;

import java.lang.annotation.*;

/**
 * User: hexie
 * Date: 2019-02-06 23:56
 * Description:
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FrameworkBizConverter {

    /**
     * @return
     */
    CosmeticsEnum type();

    /**
     * @return
     */
    String biz();
}