package com.weshare.asset.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class GenericUtils {
    @Nullable public static Class<?> getGenericType(Object object) {
        Assert.notNull(object, "传入Class对象不能为空");
        List<Type> genericSuperList = getAllGenericSuper(object);
        for (Type type : genericSuperList) {
            Class<?> result = getGenericType(type);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    private static List<Type> getAllGenericSuper(Object object) {
        List<Type> result = new ArrayList<>();
        Type superClass = object.getClass().getGenericSuperclass();
        if (superClass != null) {
            result.add(superClass);
        }

        Type[] interfaces = object.getClass().getGenericInterfaces();
        if (interfaces != null && interfaces.length > 0) {
            result.addAll(Arrays.asList(interfaces));
        }
        return result;
    }

    private static Class<?> getGenericType(Type type) {
        if (type == null) {
            return null;
        }

        Field field = ReflectionUtils.findField(type.getClass(),"actualTypeArguments");
        if (field == null) {
            return null;
        }

        field.setAccessible(true);
        Type[] types = (Type[])ReflectionUtils.getField(field, type);
        if (types == null || types.length == 0) {
            return null;
        }

        return (Class)types[0];
    }
}
