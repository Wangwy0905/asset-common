package com.weshare.asset.common.util;

import com.weshare.asset.common.exception.InvalidRequestException;
import com.weshare.asset.common.exception.InvalidResponseException;
import org.modelmapper.internal.util.Assert;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class InspectUtils {
    public static <T> T validateRequest(T request) throws InvalidRequestException {
        Assert.notNull(request, "传入参数不能为空！");
        Set<ConstraintViolation<T>> validations = InspectUtils.validate(request);
        if (validations == null || validations.size() == 0) {
            return request;
        }

        throw new InvalidRequestException(400002, validations);
    }

    public static <T> T validateResponse(T response) throws InvalidResponseException {
        Assert.notNull(response, "传入参数不能为空！");
        Set<ConstraintViolation<T>> validations = InspectUtils.validate(response);
        if (validations == null || validations.size() == 0) {
            return response;
        }

        throw new InvalidResponseException(validations);
    }

    public static <T> Set<ConstraintViolation<T>> validate(T inspectable) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(inspectable);
    }
}
