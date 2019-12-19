package com.weshare.asset.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class RateUtils {
    private static final Logger logger = LoggerFactory.getLogger(RateUtils.class);

    public static Double convertToDouble(String rate) {
        if (rate == null) {
            return null;
        }

        try {
            NumberFormat nf = NumberFormat.getPercentInstance();
            Number number = nf.parse(rate);
            return number.doubleValue();
        } catch (Exception e) {
            logger.warn("字符串转换数字类型异常", e);
        }

        return null;
    }

    public static String convertToString(BigDecimal rate) {
        if (rate == null) {
            return null;
        }

        DecimalFormat df = new DecimalFormat("0.0000%");
        return df.format(rate);
    }
}
