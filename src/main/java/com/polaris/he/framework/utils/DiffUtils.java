package com.polaris.he.framework.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DiffUtils {

    /**
     * diff source and target,field must unique in source and target
     *
     * @param source
     * @param target
     * @param uniqueFields
     * @param <T>
     * @return
     */
    public static <T> DiffResult<T> diff(Collection<T> source, Collection<T> target, Collection<Field> uniqueFields, BiFunction<T, T, Boolean> equalFunction) {
        if (CollectionUtils.isEmpty(source)) {
            return new DiffResult<>(target, new LinkedList<>(), new LinkedList<>(), new LinkedList<>());
        }
        if (CollectionUtils.isEmpty(target)) {
            return new DiffResult<>(new LinkedList<>(), source, new LinkedList<>(), new LinkedList<>());
        }

        Map<String, T> sourceMap = source.stream().collect(Collectors.toMap(l -> String.valueOf(getFieldsValueElegant(uniqueFields, l)), Function.identity()));
        Map<String, T> targetMap = target.stream().collect(Collectors.toMap(l -> String.valueOf(getFieldsValueElegant(uniqueFields, l)), Function.identity()));
        Set<String> sourceKey = sourceMap.keySet();
        Set<String> targetKey = targetMap.keySet();

        return new DiffResult<>(
                extract(SetUtils.difference(targetKey, sourceKey), targetMap, sourceMap, null),// target - source ,add
                extract(SetUtils.difference(sourceKey, targetKey), sourceMap, targetMap, null),// source - target  ,delete
                extract(SetUtils.intersection(sourceKey, targetKey), sourceMap, targetMap, equalFunction),// source|source ,equal
                extract(SetUtils.intersection(sourceKey, targetKey), sourceMap, targetMap, equalFunction.andThen(l -> !l))// source|source ,not equal
        );
    }

    private static <T> Collection<T> extract(SetUtils.SetView<String> set, Map<String, T> map0, Map<String, T> map1, BiFunction<T, T, Boolean> equalFunction) {
        if (set == null || set.isEmpty()) {
            return new LinkedList<>();
        }
        List<T> ret = new LinkedList<>();
        for (String key : set) {
            T data = map0.get(key);
            if (data != null && (equalFunction == null || equalFunction.apply(data, map1.get(key)))) {
                ret.add(data);
            }
        }
        return ret;
    }

    private static String getFieldsValueElegant(Collection<Field> fields, Object object) {
        if (CollectionUtils.isNotEmpty(fields)) {
            return fields.stream().map(l -> fieldsValueElegantGetter(l, object)).collect(Collectors.joining(" __!#@__ "));
        }
        return null;
    }

    private static String fieldsValueElegantGetter(Field field, Object object) {
        try {
            return String.valueOf(FieldUtils.readField(field, object, true));
        } catch (IllegalAccessException e) {
        }
        return "null";
    }

    @Getter
    @AllArgsConstructor
    public static class DiffResult<T> {

        private Collection<T> add;

        private Collection<T> delete;

        private Collection<T> equal;

        private Collection<T> notEqual;
    }
}
