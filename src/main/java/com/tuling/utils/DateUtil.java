package com.tuling.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 提供日期时间处理的工具类.
 *
 * @author
 */
public class DateUtil {
    /**
     * 日期格式(yyyy-MM-dd)
     */
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_PATTERN1 = "yyyy_MM_dd";

    /**
     * 日期时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间格式(HH:mm:ss)
     */
    public static final String TIME_PATTERN = "HH:mm:ss";

    /**
     * 按指定的格式({@link SimpleDateFormat})格式化时间
     *
     * @param millis  时间毫秒数.
     * @param pattern 格式
     * @return 格式化后的时间
     */
    public static String format(long millis, String pattern) {
        return format(new Date(millis), pattern);
    }

    /**
     * 时间相减得出分钟
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getDateMinus(String startDate, String endDate, String pattern) {
        SimpleDateFormat myFormatter = new SimpleDateFormat(pattern);
        try {
            Date date = myFormatter.parse(startDate);
            Date mydate = myFormatter.parse(endDate);
            long day = (mydate.getTime() - date.getTime()) / (60 * 1000);
            return day;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 实现日期加一天的方法
     *
     * @param s
     * @param n
     * @return
     */
    public static String addDay(String s, int n) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Calendar cd = Calendar.getInstance();
            cd.setTime(sdf.parse(s));
            cd.add(Calendar.DATE, n);//增加一天
            //cd.add(Calendar.MONTH, n);//增加一个月

            return sdf.format(cd.getTime());

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据年 月 获取对应的月份 天数
     */
    public static int getDaysByYearMonth(String dateString) {
        int year = Integer.parseInt(dateString.substring(0, 4));
        int month = Integer.parseInt(dateString.substring(5, 7));
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 获取某月的最后一天
     *
     * @throws
     * @Title:getLastDayOfMonth
     * @Description:
     * @param:@param year
     * @param:@param month
     * @param:@return
     * @return:String
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());

        return lastDayOfMonth;
    }

    /**
     * 把字符串转为日期
     *
     * @param strDate
     * @return
     * @throws Exception
     */
    public static Date ConverToDate(String strDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取某段时间的所有的日期
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<Date> findDates(String startTime, String endTime) {
        Date startDate = DateUtil.ConverToDate(startTime);
        Date endDate = DateUtil.ConverToDate(endTime);
        List<Date> lDate = new ArrayList<Date>();
        lDate.add(startDate);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间    
        calBegin.setTime(startDate);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间    
        calEnd.setTime(endDate);
        // 测试此日期是否在指定日期之后    
        while (endDate.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量    
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }

    /**
     * 根据日期取得星期几(返回int型)
     *
     * @param date
     * @return
     */
    public static int getWeek(Date date) {
        int[] weeks = {0, 1, 2, 3, 4, 5, 6};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return weeks[week_index];
    }

    /**
     * 计算两个日期的时间差
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static String getTimeDifference(String startTime, String endTime) {
        SimpleDateFormat dfs = new SimpleDateFormat("HH:mm:ss");//"yyyy-MM-dd HH:mm:ss.SSS"
        long between = 0;
        try {
            Date begin = dfs.parse(startTime);//"2009-07-21 11:23:21"
            Date end = dfs.parse(endTime);//"2009-07-21 11:24:49"
            between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        long day = between / (24 * 60 * 60 * 1000);
        long hour = (between / (60 * 60 * 1000) - day * 24);
        long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        //long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000
        //       - min * 60 * 1000 - s * 1000);//毫秒
        String date = day + "天" + hour + "小时" + min + "分" + s + "秒";
        if (day == 0 && hour != 0) {
            date = hour + "小时" + min + "分" + s + "秒";
        } else if (day == 0 && hour == 0 && min != 0) {
            date = min + "分" + s + "秒";
        } else if (day == 0 && hour == 0 && min == 0) {
            date = +s + "秒";
        }
        return date;
    }

    /**
     * 获取当前时间到第二天早上九点的时间的秒数
     *
     * @return
     */
    public static int getNextDaySec() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, 1);//加一天
        cal.set(Calendar.HOUR_OF_DAY, 9);//设置小时
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Long tm = cal.getTimeInMillis() - date.getTime();
        return (int) (tm / 1000);
    }

    /**
     * 根据日期取得星期几
     *
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return weekOfDays[week_index];
    }

    /**
     * 按指定的格式({@link SimpleDateFormat})格式化时间
     *
     * @param date    时间
     * @param pattern 格式
     * @return 格式化后的时间
     */
    public static String format(Date date, String pattern) {
        DateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    /**
     * 按默认的格式(yyyy-MM-dd)格式化日期
     *
     * @param date 时间
     * @return 格式化后的日期
     */
    public static String formatDate(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 按默认的格式(HH:mm:ss)格式化时间
     *
     * @param date 时间
     * @return 格式化后的时间
     */
    public static String formatTime(Date date) {
        return format(date, TIME_PATTERN);
    }

    /**
     * 按默认的格式(yyyy-MM-dd HH:mm:ss)格式化日期时间
     *
     * @param date 时间
     * @return 格式化后的日期时间.
     */
    public static String formatDateTime(Date date) {
        return format(date, DATE_TIME_PATTERN);
    }

    /**
     * 按指定的格式({@link SimpleDateFormat})格式化当前时间
     *
     * @param pattern 格式
     * @return 格式化后的当前时间
     */
    public static String formatCurrent(String pattern) {
        return format(new Date(), pattern);
    }

    /**
     * 按默认的格式(yyyy-MM-dd)格式化当前日期
     *
     * @return 格式化后的当前日期
     */
    public static String formatCurrentDate() {
        return format(new Date(), DATE_PATTERN);
    }

    /**
     * 按默认的格式(HH:mm:ss)格式化当前时间
     *
     * @return 格式化后的当前时间.
     */
    public static String formatCurrentTime() {
        return format(new Date(), TIME_PATTERN);
    }

    /**
     * 按默认的格式(yyyy-MM-dd HH:mm:ss)格式化当前日期时间
     *
     * @return 格式化后的当前日期时间.
     */
    public static String formatCurrentDateTime() {
        return format(new Date(), DATE_TIME_PATTERN);
    }

    /**
     * 获得今天凌晨0点0分对应的时间
     *
     * @return 今天凌晨0点0分对应的时间
     */
    public static Date getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得指定时间当天凌晨0点0分对应的时间
     *
     * @param date 指定的时间
     * @return 指定时间当天凌晨0点0分对应的时间
     */
    public static Date getTheDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 比较开始日期和结束日期
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return 如果start和end均为<code>null</code>或他们代表的日期相同,返回0;
     * 如果end为<code>null</code>,返回大于0的数字;
     * 如果start为<code>null</code>,则start假设为当前日期和end比较,
     * start代表日期在end代表的日期前,则返回小于0的数字,
     * start代表日期在end代表的日期后,则返回大于0的数字.
     */
    public static int compareDate(Date start, Date end) {
        if (start == null && end == null) {
            return 0;
        } else if (end == null) {
            return 1;
        } else if (start == null) {
            start = new Date();
        }
        start = getTheDate(start);
        end = getTheDate(end);
        return start.compareTo(end);
    }

    /**
     * 从指定的日期字符串中解释出时间, 解释的规则({@link SimpleDateFormat})由pattern指定
     *
     * @param dateString 日期字符串
     * @param pattern    解释规则
     * @return 时间
     */
    public static Date stringToDate(String dateString, String pattern) {
        DateFormat formatter = new SimpleDateFormat(pattern);
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 返回指定时间点 <code>amount</code> 年后(前)的时间. 返回新对象,源日期对象(<code>date</code>)不变
     *
     * @param date   源时间,不能为<code>null</code>.
     * @param amount 年数,正数为加,负数为减.
     * @return 增加(减少)指定年数后的日期对象.
     * @throws IllegalArgumentException 如果源日期对象(<code>date</code>)为<code>null</code>.
     */
    public static Date addYears(Date date, int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    /**
     * 返回指定时间点 <code>amount</code> 月后(前)的时间. 返回新对象,源日期对象(<code>date</code>)不变.
     *
     * @param date   源时间,不能为<code>null</code>.
     * @param amount 月数,正数为加,负数为减.
     * @return 增加(减少)指定月数后的日期对象.
     * @throws IllegalArgumentException 如果源日期对象(<code>date</code>)为<code>null</code>.
     */
    public static Date addMonths(Date date, int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    /**
     * 返回指定时间点 <code>amount</code> 星期后(前)的时间. 返回新对象,源日期对象(<code>date</code>)不变.
     *
     * @param date   源时间,不能为<code>null</code>.
     * @param amount 星期数,正数为加,负数为减.
     * @return 增加(减少)指定星期数后的日期对象.
     * @throws IllegalArgumentException 如果源日期对象(<code>date</code>)为<code>null</code>.
     */
    public static Date addWeeks(Date date, int amount) {
        return add(date, Calendar.WEEK_OF_YEAR, amount);
    }

    /**
     * 返回指定时间点 <code>amount</code> 天后(前)的时间. 返回新对象,源日期对象(<code>date</code>)不变.
     *
     * @param date   源时间,不能为<code>null</code>.
     * @param amount 天数,正数为加,负数为减.
     * @return 增加(减少)指定天数后的日期对象.
     * @throws IllegalArgumentException 如果源日期对象(<code>date</code>)为<code>null</code>.
     */
    public static Date addDays(Date date, int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    /**
     * 返回指定时间点 <code>amount</code> 小时后(前)的时间. 返回新对象,源日期对象(<code>date</code>)不变.
     *
     * @param date   源时间,不能为<code>null</code>.
     * @param amount 小时数,正数为加,负数为减.
     * @return 增加(减少)指定小时数后的日期对象.
     * @throws IllegalArgumentException 如果源日期对象(<code>date</code>)为<code>null</code>.
     */
    public static Date addHours(Date date, int amount) {
        return add(date, Calendar.HOUR_OF_DAY, amount);
    }

    /**
     * 返回指定时间点 <code>amount</code> 分钟后(前)的时间. 返回新对象,源日期对象(<code>date</code>)不变.
     *
     * @param date   源时间,不能为<code>null</code>.
     * @param amount 分钟数,正数为加,负数为减.
     * @return 增加(减少)指定分钟数后的日期对象.
     * @throws IllegalArgumentException 如果源日期对象(<code>date</code>)为<code>null</code>.
     */
    public static Date addMinutes(Date date, int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    /**
     * 返回指定时间点 <code>amount</code> 秒后(前)的时间. 返回新对象,源日期对象(<code>date</code>)不变.
     *
     * @param date   源时间,不能为<code>null</code>.
     * @param amount 秒数,正数为加,负数为减.
     * @return 增加(减少)指定秒数后的日期对象.
     * @throws IllegalArgumentException 如果源日期对象(<code>date</code>)为<code>null</code>.
     */
    public static Date addSeconds(Date date, int amount) {
        return add(date, Calendar.SECOND, amount);
    }

    /**
     * 返回指定时间点 <code>amount</code> 豪秒后(前)的时间. 返回新对象,源日期对象(<code>date</code>)不变.
     *
     * @param date   源时间,不能为<code>null</code>.
     * @param amount 豪秒数,正数为加,负数为减.
     * @return 增加(减少)指定豪秒数后的日期对象.
     * @throws IllegalArgumentException 如果源日期对象(<code>date</code>)为<code>null</code>.
     */
    public static Date addMilliseconds(Date date, int amount) {
        return add(date, Calendar.MILLISECOND, amount);
    }

    /**
     * 返回指定时间点增加(减少)一段时间后的时间. 返回新对象,源日期对象(<code>date</code>)不变
     *
     * @param date          源时间,不能为<code>null</code>.
     * @param calendarField 增加(减少)的日历项.
     * @param amount        数量,正数为加,负数为减.
     * @return 日历项增加(减少)指定数目后的日期对象.
     * @throws IllegalArgumentException 如果源日期对象(<code>date</code>)为<code>null</code>.
     */
    private static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("日期对象不允许为null!");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    /**
     * 返回指定时间增加（减少）自然日及小时候的日期对象
     *
     * @param date  源时间
     * @param days  增加（减少）的天数
     * @param hours 增加（减少）的小时数
     * @return 增加（减少）天、小时后的日期对象
     */
    public static Date getDateAfterNatureDays(Date date, int days, int hours) {
        if (date == null) {
            date = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        c.add(Calendar.HOUR, hours);

        return c.getTime();
    }

    /**
     * 返回指定时间增加（减少）工作日及小时候的日期对象
     *
     * @param date  源时间
     * @param days  增加（减少）的天数
     * @param hours 增加（减少）的小时数
     * @return 增加（减少）天、小时后的日期对象
     */
    public static Date getDateAfterWorkDays(Date date, int days, int hours) {
        if (date == null) {
            date = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        c.add(Calendar.HOUR, hours);

        return c.getTime();
    }

    /*
    * 将时间转换为时间戳
    */
    public static String dateToStamp(String s) throws ParseException{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
