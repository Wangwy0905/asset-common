package com.weshare.asset.common.util;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PropertyAdviceUtilsTest {

    @Test
    public void get() {
        B b = new B();
        b.setTest(Arrays.asList("llala","asf"));
        A a = new A();
        a.setB(b);

        System.out.println(PropertyAdviceUtils.get(a,"b.test",ArrayList.class));
    }
}

@Data
class A{
    private B b;
}

@Data
class B {
    private List<String> test;
}
