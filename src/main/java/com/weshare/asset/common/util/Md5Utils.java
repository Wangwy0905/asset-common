package com.weshare.asset.common.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.lang.Nullable;

/**
 * MD5工具类
 * @author zhibin.wang
 */
public class Md5Utils {
    @Nullable
    public static String md5Hex(@Nullable String data) {
        if (data == null) {
            return null;
        }

        return DigestUtils.md5Hex(data);
    }
}
