package com.weshare.asset.common.util;

import com.weshare.asset.common.convert.ExtensibleEntityConverter;
import com.weshare.asset.common.convert.GenericConverter;
import com.weshare.asset.common.convert.NothingConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.support.DefaultConversionService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ConversionUtils {
    private static final DefaultConversionService conversionService;
    static {
        conversionService = new DefaultConversionService();
        conversionService.addConverter(new NothingConverter());
        conversionService.addConverter(new ExtensibleEntityConverter());
        conversionService.addConverter(new GenericConverter());
    }

    public static <T> T convert(Object source, Class<T> type) {
        return conversionService.convert(source, type);
    }

    public static <T> List<T> convertList(List<?> source, Class<T> type) {
        if (source == null) {
            return null;
        }

        return source.stream().map(obj -> {
            return conversionService.convert(obj, type);
        }).collect(Collectors.toList());
    }
}
