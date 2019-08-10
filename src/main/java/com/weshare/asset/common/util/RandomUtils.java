package com.weshare.asset.common.util;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.internal.util.Assert;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

@Slf4j
public class RandomUtils {
    private static final char[] CHAR_32 = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9'};

    private static final char[] PURE_NUM = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private static SecureRandom random;

    static {
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            log.warn("构造随机数生成器失败", e);
        }
    }

    /**
     * 获取真随机字符串
     *
     * @param length
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static char[] createRandomChar(int length) {
        Assert.isTrue(length > 0, "长度不能小于0");

        char[] result = new char[length];

        for (int i = 0; i < result.length; i++) {
            result[i] = CHAR_32[random.nextInt(CHAR_32.length)];
        }

        return result;
    }

    /**
     * 获取真随机纯数字字符串
     *
     * @param length
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static char[] createRandomNum(int length) {
        Assert.isTrue(length > 0, "长度不能小于0");

        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = PURE_NUM[random.nextInt(PURE_NUM.length)];
        }

        return result;
    }

    public static final String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static boolean createRandomBoolean() {
        return random.nextInt() % 2 == 0;
    }

    public static short createRandomShort() {
        return (short)random.nextInt();
    }

    public static int createRandomInteger() {
        return random.nextInt();
    }

    public static long createRandomLong() {
        return random.nextLong();
    }

    public static float createRandomFloat() {
        return random.nextFloat();
    }

    public static double createRandomDouble() {
        return random.nextDouble();
    }
}
