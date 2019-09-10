package com.weshare.asset.common.util;

import org.springframework.util.Assert;

import java.text.NumberFormat;

public class Rateutils {

    public static Double convertToDouble(String rate) {
        Assert.notNull(rate, "费率不能为空");
        try {
            NumberFormat nf = NumberFormat.getPercentInstance();
            Number number = nf.parse(rate);
            return number.doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
