package com.polaris.he.framework.repository;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.polaris.he.framework.annotation.FrameworkBizConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;

/**
 * User: hexie
 * Date: 2019-02-06 23:59
 * Description:
 */
@Slf4j
@Component
public class FrameworkBizConverterRepository implements ApplicationContextAware {

    private static final Table<String, String, Converter<?, ?>> TABLE = HashBasedTable.create();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Converter> beans = applicationContext.getBeansOfType(Converter.class);
        if (MapUtils.isNotEmpty(beans)) {
            beans.forEach((beanName, bean) -> {
                Class<? extends Converter> clazz = bean.getClass();
                FrameworkBizConverter ann = clazz.getAnnotation(FrameworkBizConverter.class);
                if (ann != null) {
                    Type convert = Arrays.stream(clazz.getGenericInterfaces()).filter(l -> l instanceof ParameterizedType && ((ParameterizedType) l).getRawType().equals(Converter.class)).findFirst().orElse(null);
                    if (convert != null) {
                        Type source = ((ParameterizedType) convert).getActualTypeArguments()[0];
                        log.info("扫描到FrameworkBizConverter，ann={},source={}", ann, source);
                        TABLE.put(ann.type().getCode(), bizKey(source, ann.biz()), bean);
                    }
                }
            });
        }
    }

    private String bizKey(Type type, String biz) {
        return String.format("%s(%s)", type, biz);
    }

    @SuppressWarnings("unchecked")
    public <T> Converter<T, ?> getConverter(String type, Class<T> clazz, String biz) {
        Converter<T, ?> converter = (Converter<T, ?>) TABLE.get(type, bizKey(clazz, biz));
        Assert.notNull(converter, "convert为空");
        return converter;
    }
}