package com.weshare.asset.common.util;

import org.springframework.util.Assert;

import java.util.Calendar;
import java.util.Date;

/**
 * @author ：yuchen.hu
 * @date ：2019/6/25
 * @description：
 */
public class DateUtils {

    public static Date getNextMonthDate(Date date) {
        Assert.notNull(date,"date must not be null.");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }
}
