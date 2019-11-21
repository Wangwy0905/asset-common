package com.weshare.asset.common.util;

import com.weshare.asset.common.exception.ServiceException;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ImageUtils {

    /**
     * 校验base64是否为BMP、GIF、JPG、PNG格式
     * @param imgBase64Str base64字符串
     * @return true: 格式正确
     */
    public static boolean isImage(String imgBase64Str) throws ServiceException {
        if (imgBase64Str == null || imgBase64Str.length() == 0) {
            return false;
        } else {

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] byteArray = new byte[0];
            try {
                byteArray = decoder.decodeBuffer(imgBase64Str);
            } catch (IOException e) {
               throw new ServiceException("base64解码失败", e);
            }

            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray)) {
                BufferedImage bufImg = ImageIO.read(byteArrayInputStream);
                if (bufImg == null) {
                    return false;
                }
                bufImg = null;
            } catch (IOException e) {
                throw new ServiceException("base64转图片异常", e);
            }
        }
        return true;
    }
}
