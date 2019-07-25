package com.weshare.asset.common.util;

import java.util.UUID;

public class RandomUtils {
    public static final String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
