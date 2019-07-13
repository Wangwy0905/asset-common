package com.weshare.asset.common.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class InspectUtils {
    public static <T> Set<ConstraintViolation<T>> validate(T inspectable) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(inspectable);
    }
}
