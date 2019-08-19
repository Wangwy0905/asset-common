package com.weshare.asset.common.util;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.weshare.asset.common.util.DateUtils.periodBeforeNow;

public class DateUtilsTest {
    @Test
    public void testPeriod() throws ParseException {
        System.out.println(periodBeforeNow(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-08-19 12:00:01")));
    }

    @Test
    public void testInHours() {
        System.out.println(DateUtils.isInHours(Calendar.getInstance().getTime(), 10));
    }
}