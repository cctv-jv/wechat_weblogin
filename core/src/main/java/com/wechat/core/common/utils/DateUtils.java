package com.wechat.core.common.utils;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author ruqiang
 * @version 1.0.0
 * @className: DateUtils
 * @Description DateUtils
 * @create 2019/11/21
 */
@Component
public class DateUtils {
    /**
     * 默认日期格式
     */
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 如2018 0901 232211(年月日时分秒)
     *
     * @return
     */
    public String yyyyMMddHHmmss() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * 如20180901
     *
     * @return
     */
    public static String getYYYYMMdd() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * 如180901
     * @return
     */
    public String getYYMMdd() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * 如201809
     * @return
     */
    public String getYYYYMM() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * 如 2018/02/11
     * @return
     */
    public String getQueryEndDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * 如 2018/02/11
     * @return
     */
    public String getQueryStartDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -30);
        return sdf.format(cal.getTime());
    }

    /**
     * 如 2018/02/11 12:30:00
     * @return
     */
    public static String getQueryEndTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * 如 2018/02/11
     * @return
     */
    public String getQueryStartTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -30);
        return sdf.format(cal.getTime());
    }

    public String getQueryTwoAgoDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -2);
        return sdf.format(cal.getTime());
    }

    /**
     * 字符串转换成日期
     *
     * @param str 字符串
     * @param format 日期格式
     * @return 日期
     */
    public static Date str2Date(String str, String format) {
        if (null == str || "".equals(str)) {
            return null;
        }
        // 如果没有指定字符串转换的格式，则用默认格式进行转换
        if (null == format || "".equals(format)) {
            format = DEFAULT_FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(str);
            return date;
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * @param date 日期
     * @param format 日期格式
     * @return 字符串
     */
    public static String date2Str(Date date, String format) {
        if (null == date) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 时间戳转换为字符串
     * @param time
     * @return
     */
    public static String timestamp2Str(Timestamp time) {
        Date date = new Date(time.getTime());
        return date2Str(date, DEFAULT_FORMAT);
    }

    /**
     * 字符串转换为时间
     * @param str
     * @return
     */
    public static Timestamp str2Timestamp(String str) {
        Date date = str2Date(str, DEFAULT_FORMAT);
        return new Timestamp(date.getTime());
    }

    /**
     * 字符串转换为时间
     * @param str
     * @return
     */
    public static Timestamp str2Timestamp(String str, String formatStr) {
        if (null == str) {
            return null;
        }
        Date date = str2Date(str, formatStr);
        return new Timestamp(date.getTime());
    }

    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     * @param year  年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }

    @SuppressWarnings("static-access")
    public static Date getnextLast(String datetime, int year) {
        Calendar calendar = new GregorianCalendar();
        Date date = null;
        if (datetime.length() > 7) {
            date = str2Date(datetime, "yyyy-MM-dd");
        } else {
            date = str2Date(datetime, "yyyy-MM");
        }
        calendar.setTime(date);
        // 把日期往后增加一年.整数往后推,负数往前移动
        calendar.add(Calendar.YEAR, year);
        date = calendar.getTime();
        return date;

    }

    public static String getrightDate(String datetime, int year) {
        String date = "";
        String years = datetime.substring(0, 4);
        String dates = datetime.substring(4, datetime.length());
        Integer s = new Integer(years) + year;
        date = s + dates;
        return date;
    }

    public static void main(String[] args) {
        System.out.println(getrightDate("2018-09", 4));
        System.out.println(date2Str(getnextLast("2018-09", 4), "yyyy-MM"));
    }
}