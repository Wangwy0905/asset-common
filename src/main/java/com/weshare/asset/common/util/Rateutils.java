package com.weshare.asset.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Rateutils {

    public static Double convertToDouble(String rate) {
        if (rate == null) {
            return null;
        }

        try {
            NumberFormat nf = NumberFormat.getPercentInstance();
            Number number = nf.parse(rate);
            return number.doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertToString(BigDecimal rate) {
        if (rate == null) {
            return null;
        }

        DecimalFormat df = new DecimalFormat("0.00%");
        return df.format(rate);
    }
}
