package com.weshare.asset.common.util;

import com.weshare.asset.common.exception.AssetException;
import com.weshare.asset.common.exception.ServiceException;
import org.springframework.util.Assert;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ImageUtils {

    /**
     * 校验base64是否为BMP、GIF、JPG、PNG格式
     *
     * @param imgBase64Str base64字符串
     * @return true: 格式正确
     */
    public static boolean isImage(String imgBase64Str) throws AssetException {
        if (imgBase64Str == null || imgBase64Str.length() == 0) {
            return false;
        }

        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decodeBuffer(imgBase64Str))) {
            BufferedImage bufImg = ImageIO.read(byteArrayInputStream);
            if (bufImg == null) {
                return false;
            }
            return true;
        } catch (IOException e) {
            throw new ServiceException("base64转图片异常", e);
        }

    }

    private static byte[] decodeBuffer(String imgBase64Str) throws AssetException {
        Assert.isTrue(imgBase64Str != null && imgBase64Str.length() != 0, "传入的字符串不为null或空串");
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            return decoder.decodeBuffer(imgBase64Str);
        } catch (IOException e) {
            throw new ServiceException("base64解码失败", e);
        }
    }
}
