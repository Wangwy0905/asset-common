package com.weshare.asset.common.model;

import com.weshare.asset.common.util.ConversionUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Response<T> {
    //成功
    public static final int SUCCESS = 200;
    //非业务类异常
    public static final int SYSTEM_ERROR = 500;
    //业务类异常
    public static final int BUSINESS_ERROR = 400;
    //非业务类异常 message描述
    public static final String SYSTEM_ERROR_MESSAGE = "服务器端错误，请稍后再试!";

    private int status;
    private T payload;
    private String message;

    public Response(int status, T payload) {
        this.status = status;
        this.payload = payload;
    }

    public Response(int status, String message, T payload) {
        this.status = status;
        this.message = message;
        this.payload = payload;
    }

    public static <T> Response<List<T>> success(List<?> sourceList, Class<T> type) {
        List<T> targetList = new ArrayList<T>();

        for (Object sourceObj : sourceList) {
            targetList.add(ConversionUtils.convert(sourceObj, type));
        }

        return success(targetList);
    }

    public static <T> Response<T> success(Object sourceObj, Class<T> type) {
        return success(ConversionUtils.convert(sourceObj, type));
    }

    public static <T> Response<T> success(T payload) {
        return success(null, payload, null);
    }

    public static Response success(String message) {
        return success(message, null, null);
    }

    public static Response success(String message, Object... args) {
        return success(message, null, args);
    }

    public static <T> Response<T> success(String message, T payload, Object... args) {
        if (message == null) return new Response(SUCCESS, payload);

        return new Response(SUCCESS, String.format(message, args), payload);
    }

    public static Response systemFail() {
        return systemFail(SYSTEM_ERROR_MESSAGE, null);
    }

    public static Response systemFail(String message) {
        return systemFail(message, null);
    }

    public static Response systemFail(String message, Object... args) {
        if (message == null) return new Response(SYSTEM_ERROR, null, null);
        return new Response(SYSTEM_ERROR, String.format(message, args), null);
    }

    public static Response businessFail(String message) {
        return businessFail(message, null);
    }

    public static Response businessFail(String message, Object... args) {
        if (message == null) return new Response(BUSINESS_ERROR, null, null);
        return new Response(BUSINESS_ERROR, String.format(message, args), null);
    }

}
