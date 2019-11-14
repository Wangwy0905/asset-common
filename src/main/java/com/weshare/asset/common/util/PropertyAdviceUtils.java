package com.weshare.asset.common.util;

import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

public class PropertyAdviceUtils {


    /**
     * @param object
     * @param path   a.b.c
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T get(Object object, String path, Class<T> tClass) {
        List<String> pathList = Arrays.asList(path.split("\\."));
        return get(object, pathList, tClass);
    }

    public static <T> T get(Object object, List<String> pathList, Class<T> tClass) {

        if (CollectionUtils.isEmpty(pathList)) return ConversionUtils.convert(object, tClass);

        Object s = PropertyUtils.get(object, pathList.get(0), Object.class);

        if(s == null) return null;

        return get(s, pathList.subList(1, pathList.size()), tClass);
    }




}


