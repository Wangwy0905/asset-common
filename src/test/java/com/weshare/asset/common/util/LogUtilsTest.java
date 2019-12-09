package com.weshare.asset.common.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.Data;
import org.junit.Test;

import java.io.IOException;

public class LogUtilsTest {

    @Test
    public void testJacksonCustomizeSerializer() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(String.class, new StringLogSerializer());

        mapper.registerModule(module);

        Target target = new Target();
        System.out.println(mapper.writeValueAsString(target));
    }

    @Data
    static class Target {
        String name = "Hello World";
        String value = "Hello World Value";
    }

    static class StringLogSerializer extends JsonSerializer<String> {
        @Override
        public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value == null) {
                gen.writeString(value);
            }

            gen.writeString(value.substring(0, 4));
        }
    }
}
