package com.polaris.he.framework.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * User: hexie
 * Date: 2019-01-19 00:06
 * Description:
 */
@Slf4j
public class BeanCopyUtils {

    public static <T, R> T copyObject(R source, T target) {
        BeanUtils.copyProperties(source, target);
        return target;
    }

    public static <T, R> T copyObject(R source, Class<T> targetClass) {
        try {
            T target = targetClass.getConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error("newInstance error", e);
        }
        return null;
    }
}