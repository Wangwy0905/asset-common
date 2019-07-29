package com.weshare.asset.common.util;

import com.weshare.asset.common.exception.ServiceException;
import lombok.Data;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ResourceUtilsTest {
    @Test
    public void testRead() throws ServiceException {
        Sample sample = ResourceUtils.deserialize("mock/test/sample.json", Sample.class);
        assertThat(sample.getName(), is("Hello"));
    }

    @Data
    static class Sample {
        private String name;
        private String property;
    }
}