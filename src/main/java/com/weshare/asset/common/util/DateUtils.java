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

    public static Date addDate(Date date, long day) {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
        time += day; // 相加得到新的毫秒数
        return new Date(time); // 将毫秒数转换成日期
    }
}
