package com.weshare.asset.common.exception;

import com.weshare.asset.common.model.Response;
import com.weshare.asset.common.util.ConversionUtils;
import com.weshare.asset.common.util.POJOUtils;
import lombok.Data;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

public class InvalidResponseException extends AssetCallRemoteException {
    public InvalidResponseException() {
        super(Response.BUSINESS_ERROR, "返回参数不正确！");
    }
    public InvalidResponseException(Throwable ex) {
        super(Response.BUSINESS_ERROR, "返回参数不正确！", ex);
    }
    public InvalidResponseException(Integer code, String message) {
        super(code, message);
    }
    public InvalidResponseException(String message) {
        super(Response.BUSINESS_ERROR, message);
    }
    public InvalidResponseException(Integer code, String message, Throwable ex) {
        super(code, message, ex);
    }

    public <T> InvalidResponseException(Set<ConstraintViolation<T>> validations) {
        super(Response.BUSINESS_ERROR, POJOUtils.toString(validations.stream().map(validation -> {
            Violation violation = ConversionUtils.convert(validation, Violation.class);
            return violation;
        }).collect(Collectors.toList())));
    }

    @Data
    static class Violation {
        private String path;
        private String message;
        private String propertyPath;
    }
}
