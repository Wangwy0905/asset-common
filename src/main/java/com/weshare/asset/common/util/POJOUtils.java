package com.weshare.asset.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.util.List;

/**
 * 一些工具方法
 * @author zhibin.wang
 */
public class POJOUtils {
    @Nullable
    public static <E> String toString(E entity) {
        if (entity == null) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        try {
            return mapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static <T> T deserialize(String jsonStr, Class<T> type) {
        if (jsonStr == null) {
            return null;
        }

        if (type.equals(String.class)) {
            return (T)readStr(jsonStr);
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonStr, type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String readStr(String jsonStr) {
        if (jsonStr == null) {
            return null;
        }

        if (jsonStr.startsWith("\"") && jsonStr.endsWith("\"")) {
            return jsonStr.substring(1, jsonStr.length() - 1);
        }

        return jsonStr;
    }

    @Nullable
    public static <T> List<T> deserializeList(String jsonStr, Class<T> type) {
        if (jsonStr == null) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, type);
            return (List<T>)mapper.readValue(jsonStr, javaType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
