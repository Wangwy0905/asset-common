package com.weshare.asset.common.util;

import com.weshare.asset.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.Assert;
import org.springframework.util.Base64Utils;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
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

    public static String createBase64(InputStream in) throws BizException {
        byte[] buff = new byte[100];
        int rc = 0;
        try (ByteArrayOutputStream swapStream = new ByteArrayOutputStream()) {
            while ((rc = in.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            return new String(Base64.encodeBase64(swapStream.toByteArray()));
        } catch (IOException e) {
            throw new BizException(500006, "inputStream格式文件转base64异常", e);
        }
    }

    public static String createBase64(RenderedImage image) throws BizException {
        Assert.notNull(image, "传入图像文件不能为空");

        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            ImageIO.write(image, "png", out);
            out.flush();
            byte[] picBytes = out.toByteArray();
            return Base64Utils.encodeToString(picBytes);
        } catch (IOException e) {
            throw new BizException(500005, "图片转base64异常", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    log.warn("文件流关闭异常", e);
                }
            }
        }
    }
}
