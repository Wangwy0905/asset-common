package com.weshare.asset.common.util;

import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ：yuchen.hu
 * @date ：2019/6/25
 * @description：
 */
public class DateUtils {
    public static Date midnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        //关键的一步，很多时候忽略了毫秒置0，而无法查询出想要的结果
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date tomorrowMidnight() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static boolean before(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        return calendar1.before(calendar2);
    }

    public static boolean after(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        return calendar1.after(calendar2);
    }

    public static boolean isInHours(Date date, int... hours) {
        Assert.notNull(date, "日期不能为空");

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int hour = cal.get(Calendar.HOUR_OF_DAY);
        for (int i = 0; i < hours.length; i++) {
            if (hour == hours[i]) {
                return true;
            }
        }

        return false;
    }

    public static int yearBeforeNow(Date date) {
        Assert.notNull(date, "日期不能为空");

        LocalDate now = LocalDate.now();
        LocalDate theDay = midnight(date);

        return (int) theDay.until(now, ChronoUnit.YEARS);
    }

    public static int monthBeforeNow(Date date) {
        Assert.notNull(date, "日期不能为空");

        LocalDate now = LocalDate.now();
        LocalDate theDay = midnight(date);

        return (int) theDay.until(now, ChronoUnit.MONTHS);
    }

    public static long dayBeforeNow(Date date) {
        Assert.notNull(date, "日期不能为空");
        LocalDate now = LocalDate.now();
        LocalDate theDay = midnight(date);

        return theDay.until(now, ChronoUnit.DAYS);
    }

    public static LocalDate midnight(Date date) {
        Assert.notNull(date, "日期不能为空");
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date convert(LocalDateTime date) {
        if (date == null) {
            return null;
        }

        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime dateConvertLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }

        return  date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Date addDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,day);
        Date time = calendar.getTime();
        return time;
    }

    public static Date addMouth(Date date, int mouth) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,mouth);
        Date time = calendar.getTime();
        return time;
    }

}
