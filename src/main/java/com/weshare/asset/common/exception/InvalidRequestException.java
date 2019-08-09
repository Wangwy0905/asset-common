package com.weshare.asset.common.exception;

import com.weshare.asset.common.model.Response;
import com.weshare.asset.common.util.ConversionUtils;
import com.weshare.asset.common.util.POJOUtils;
import lombok.Data;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

public class InvalidRequestException extends AssetCallRemoteException {
    public InvalidRequestException() {
        super(Response.BUSINESS_ERROR, "请求参数错误！");
    }
    public InvalidRequestException(Throwable ex) {
        super(Response.BUSINESS_ERROR, "请求参数错误！", ex);
    }
    public InvalidRequestException(Integer code, String message) {
        super(code, message);
    }
    public InvalidRequestException(String message) {
        super(Response.BUSINESS_ERROR, message);
    }
    public InvalidRequestException(Integer code, String message, Throwable ex) {
        super(code, message, ex);
    }

    public <T> InvalidRequestException(Integer code, Set<ConstraintViolation<T>> validations) {
        super(code, POJOUtils.toString(validations.stream().map(validation -> {
            Violation violation = ConversionUtils.convert(validation, Violation.class);
            return violation;
        }).collect(Collectors.toList())));
    }

    public <T> InvalidRequestException(Set<ConstraintViolation<T>> validations) {
        super(Response.BUSINESS_ERROR, POJOUtils.toString(validations.stream().map(validation -> {
            Violation violation = ConversionUtils.convert(validation, Violation.class);
            return violation;
        }).collect(Collectors.toList())));
    }

    @Data
    static class Violation {
        private String message;
        private String propertyPath;
    }
}