package com.weshare.asset.common.model;

import com.weshare.asset.common.util.POJOUtils;
import lombok.Data;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ExtensibleEntityTest {

    @Test
    public void testConvert() {
        String jsonStr = "{\"property1\" : \"value1\", \"property2\" : \"value2\"}";
        SampleEntity entity = POJOUtils.deserialize(jsonStr, SampleEntity.class);

        // 构造对象中包含传入属性
        assertThat(entity.getProperty1(), is("value1"));

        String resultStr = POJOUtils.toString(entity);
        assertThat(resultStr, containsString("property2"));
    }

    @Data
    static class SampleEntity extends ExtensibleEntity {
        private String property1 = null;
    }
}
