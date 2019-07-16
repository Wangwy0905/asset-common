package com.weshare.asset.common.util;

import org.springframework.lang.Nullable;

/**
 * 通过Jackson的注解进行对象间的互相转换，整个过程需要先将对象转成String，再将String转成对象
 * 相对比较耗时，后续可以看是否有更好地框架可以支持该功能
 * @author zhibin.wang
 */
public class JacksonConversionUtils {
    @Nullable
    public static <S, T> T convert(S source, Class<T> type) {
        if (source == null) {
            return null;
        }

        String jsonStr = POJOUtils.toString(source);
        return POJOUtils.deserialize(jsonStr, type);
    }

    @Nullable
    public static <S, T> T merge(S source, T target, Class<T> type) {
        if (source == null) {
            return target;
        }

        T delta = convert(source, type);
        if (target == null) {
            return delta;
        }

        return EntityUtils.entityMerge(target, delta);
    }
}
