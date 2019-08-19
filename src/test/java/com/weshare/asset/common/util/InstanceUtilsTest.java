package com.weshare.asset.common.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class InstanceUtilsTest {
    @Test
    public void testLocalHost() {
        String localhost = InstanceUtils.getLocalHost();
        System.out.println(localhost);

        int index = localhost.lastIndexOf(".");
        System.out.println(index);

        localhost = localhost.substring(index + 1);
        System.out.println(localhost);
    }
}