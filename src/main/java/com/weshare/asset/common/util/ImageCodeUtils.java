package com.weshare.asset.common.util;

import com.weshare.asset.common.exception.BizException;
import com.weshare.asset.common.util.FileCoderUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.Base64Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: kevin
 * @date: 2019/4/25
 */
@Component
public class ImageCodeUtils {
    private static int width = 90;
    private static int height = 20;
    private static int xx = 15;
    private static int fontHeight = 18;
    private static int codeY = 16;
    private static int number = 30;

    private static SecureRandom random = new SecureRandom();

    public static String createCaptcha(char[] code) throws BizException {
        Assert.notNull(code, "传入的验证码不能为空");
        Assert.isTrue(code.length > 0, "传入的验证码的位数不正确");

        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics gd = buffImg.getGraphics();

        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, width, height);

        gd.setFont(new Font("Fixedsys", Font.BOLD, fontHeight));
        gd.setColor(Color.BLACK);
        gd.drawRect(0, 0, width - 1, height - 1);

        gd.setColor(Color.BLACK);
        for (int i = 0; i < number; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }

        int red, green, blue;
        for (int i = 0; i < code.length; i++) {
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);

            gd.setColor(new Color(red, green, blue));
            gd.drawString(String.valueOf(code[i]), (i + 1) * xx, codeY);
        }

        return FileCoderUtils.createBase64(buffImg);
    }
}
