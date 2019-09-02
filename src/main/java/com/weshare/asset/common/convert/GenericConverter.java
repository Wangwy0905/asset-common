package com.weshare.asset.common.convert;

import com.weshare.asset.common.annotation.Jackson;
import com.weshare.asset.common.util.JacksonConversionUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.util.Set;

@Slf4j
public class GenericConverter implements ConditionalGenericConverter {
    private static final ModelMapper mapper = new ModelMapper();

    static {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (sourceType.getType() == targetType.getType()) return false;
        return true;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return null;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        // if (targetType.getType().isAnnotationPresent(Jackson.class)) {
            return JacksonConversionUtils.convert(source, targetType.getType());
        // }
        // return mapper.map(source, targetType.getType());
    }
}
