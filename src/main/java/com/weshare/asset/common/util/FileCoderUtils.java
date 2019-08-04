package com.weshare.asset.common.util;

import com.weshare.asset.common.exception.BizException;
import org.springframework.util.Assert;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileCoderUtils {
    public static InputStream createInputStream(String fileBase64) throws BizException {
        Assert.notNull(fileBase64, "传入的文件内容不能为空");

        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes = decoder.decodeBuffer(fileBase64);
            return new ByteArrayInputStream(bytes);
        } catch (IOException e) {
            throw new BizException(500005, "base64格式文件转inputStream异常", e);
        }
    }
}
