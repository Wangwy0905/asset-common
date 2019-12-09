package com.weshare.asset.common.util;

import com.weshare.asset.common.convert.ExtensibleEntityConverter;
import com.weshare.asset.common.convert.GenericConverter;
import com.weshare.asset.common.convert.NothingConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.lang.Nullable;

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

    @Nullable
    public static <T> T convert(@Nullable Object source, Class<T> type) {
        if (source == null) {
            return null;
        }

        return conversionService.convert(source, type);
    }

    public static <T> List<T> convertList(List<?> source, Class<T> type) {
        if (source == null) {
            return null;
        }

        return source.stream().map(obj -> conversionService.convert(obj, type)).collect(Collectors.toList());
    }
}
