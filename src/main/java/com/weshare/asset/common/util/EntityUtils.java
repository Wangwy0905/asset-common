package com.weshare.asset.common.util;

import com.weshare.asset.common.entity.EntityBase;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class EntityUtils {


    public static <T> T entityMerge(T target, Object source) {
        return PropertyUtils.override(target, source);
    }

    public static <T> T entityMerge(T target, Object source, Boolean ignoreEmpty) {
        return PropertyUtils.override(target, source, ignoreEmpty);
    }

    /**
     * 属性拷贝，忽略creator字段
     *
     * @param target
     * @param source
     * @param <T>
     * @return
     */
    public static <T> T entityUpdateMerge(T target, Object source) {
        return PropertyUtils.override(target, source, true, "creator");
    }

    /**
     * 属性拷贝，忽略id字段
     *
     * @param target
     * @param source
     * @param <T>
     * @return
     */
    public static <T> T entityPropertyMerge(T target, Object source) {
        return PropertyUtils.override(target, source, true, "id");
    }

    /**
     * 属性拷贝，忽略id字段
     *
     * @param target
     * @param source
     * @param <T>
     * @return
     */
    public static <T> T entityPropertyMerge(T target, Object source, String... ignoreProperties) {
        String[] _ignoreProperties = new String[ignoreProperties.length + 1];
        _ignoreProperties[0] = "id";
        for (int i = 1; i < _ignoreProperties.length; i++)
            _ignoreProperties[i] = ignoreProperties[i - 1];

        return PropertyUtils.override(target, source, true, _ignoreProperties);
    }

    public static <T extends EntityBase> T initEntity(T target) {
        target.setCreateDateTime(LocalDateTime.now());
        target.setUpdateDateTime(LocalDateTime.now());
        target.setModifier("ADMIN");
        target.setCreator("ADMIN");
        return target;
    }

    public static <T extends EntityBase> Iterable<T> overrideOperator(Iterable<T> entities, String username) {
        if (entities == null) {
            return null;
        }

        entities.forEach(entity -> overrideOperator(entity, username));
        return entities;
    }

    public static <T extends EntityBase> T overrideOperator(T entity, String username) {
        entity.setCreator(username);
        entity.setModifier(username);
        return entity;
    }

    public static <T extends EntityBase> List<Date> getCreateDateList(List<T> entityList) {
        if (entityList == null) {
            return null;
        }

        return entityList.stream().map(entity -> {
            LocalDateTime localDateTime = entity.getCreateDateTime();
            return DateUtils.convert(localDateTime);
        }).collect(Collectors.toList());
    }
}
