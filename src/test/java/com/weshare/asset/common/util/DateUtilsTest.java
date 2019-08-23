package com.weshare.asset.common.util;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import static com.weshare.asset.common.util.DateUtils.dayBeforeNow;

public class DateUtilsTest {
    @Test
    public void testPeriod() throws ParseException {
        System.out.println(dayBeforeNow(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-08-19 12:00:01")));
    }

    @Test
    public void testInHours() {
        System.out.println(DateUtils.isInHours(Calendar.getInstance().getTime(), 10));
    }

    @Test
    public void testYearPeriod() throws ParseException {
        System.out.println(DateUtils.yearBeforeNow((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2019-08-29 12:00:01")));
        System.out.println(DateUtils.yearBeforeNow((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2019-08-22 12:00:01")));
        System.out.println(DateUtils.yearBeforeNow((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2019-08-21 12:00:01")));
        System.out.println(DateUtils.yearBeforeNow((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2018-08-22 12:00:01")));
    }

    @Test
    public void testConvert() throws ParseException {
        Date date1 = DateUtils.convert(LocalDateTime.now());
        System.out.println(date1);
        System.out.println(DateUtils.monthBeforeNow((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2019-07-23 08:00:01")));
    }
}