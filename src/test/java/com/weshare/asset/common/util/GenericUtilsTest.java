package com.weshare.asset.common.util;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GenericUtilsTest {
    @Test
    public void testGetGenericType() {
        assertThat(GenericUtils.getGenericType(new Child()).getSimpleName(), is("String"));
    }

    static class Parent<T> {

    }

    static class Child extends Parent<String> {

    }
}