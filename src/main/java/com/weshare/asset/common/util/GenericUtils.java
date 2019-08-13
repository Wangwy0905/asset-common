package com.weshare.asset.common.util;

import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class GenericUtils {
    public static Class<?> getGenericType(Object object) {
        Assert.notNull(object, "传入Class对象不能为空");
        Type type = object.getClass().getGenericSuperclass();

        if (type == null) {
            throw new IllegalArgumentException("传入参数异常，无法获取其泛型类型的父类对象");
        }

        Field field = ReflectionUtils.findField(type.getClass(),"actualTypeArguments");
        if (field == null) {
            throw new IllegalArgumentException("传入参数异常，无法获取其泛型类型的父类对象");
        }

        field.setAccessible(true);
        Type[] types = (Type[])ReflectionUtils.getField(field, type);
        if (types == null || types.length == 0) {
            throw new IllegalArgumentException("传入参数异常，无法获取其泛型类型的父类对象");
        }

        return (Class)types[0];
    }
}
