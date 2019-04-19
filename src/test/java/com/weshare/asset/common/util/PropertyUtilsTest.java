package com.weshare.asset.common.util;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PropertyUtilsTest {
    @Test
    public void testGetProperty() {
        Map<String, String> object = new HashMap<>();
        object.put("name", "wangzhibin");
        assertThat(PropertyUtils.get(object, "name", String.class), is("wangzhibin"));
    }
}