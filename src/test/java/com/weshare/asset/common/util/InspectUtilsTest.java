package com.weshare.asset.common.util;

import lombok.Data;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class InspectUtilsTest {
    @Test public void testValidate() {
        User user = new User();

        Set<ConstraintViolation<User>> validations = InspectUtils.validate(user);
        assertThat(validations, is(notNullValue()));

        for (ConstraintViolation<User> validation : validations) {
            System.out.println(validation.getPropertyPath().toString());
            System.out.println(validation.getMessage());
        }
    }

    @Data
    public static class User {
        @NotNull
        private String name;
        @NotNull
        private String address;
    }
}