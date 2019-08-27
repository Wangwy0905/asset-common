package com.weshare.asset.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import java.io.IOException;

@Slf4j
public class LogUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(String.class, new LogStringSerializer());
        objectMapper.registerModule(simpleModule);
    }

    @Nullable
    public static <E> String toString(E entity) {
        if (entity == null) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            log.warn("日志打印失败，无法序列化需打印对象", e);
            return null;
        }
    }

    static class LogStringSerializer extends JsonSerializer<String> {
        @Override
        public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value == null) {
                gen.writeString(value);
                return ;
            }

            gen.writeString(value.substring(0, 512));
        }
    }
}
