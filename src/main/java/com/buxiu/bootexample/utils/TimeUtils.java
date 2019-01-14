package com.buxiu.bootexample.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 日期转换
 * 
 * @author Anthony
 */
public class TimeUtils {

	public static final int DECEMBER = 12; // 一年的月份数量
	public static final String[] MONTHS = { "1", "2", "3", "4", "5", "6", "7",
			"8", "9", "10", "11", "12" }; // 一年的月份列表
	public static final long DAYMILLI = 24 * 60 * 60 * 1000; // 一天的毫秒数
	public static final long HOURMILLI = 60 * 60 * 1000; // 一小时的毫秒数
	public static final long MINUTEMILLI = 60 * 1000; // 一分钟的毫秒数
	public static final long SECONDMILLI = 1000; // 一秒的毫秒数
	public static final String DB_DATE_PATTERN_41 = "MM/dd";
	public static final String DB_DATE_PATTERN_42 = "MM-dd";
	public static final String DB_DATE_PATTERN_61 = "yyyy/MM";
	public static final String DB_DATE_PATTERN_62 = "yyyy-MM";
	public static final String DB_DATE_PATTERN_81 = "yyyy/MM/dd"; //
	public static final String DB_DATE_PATTERN_82 = "yyyy-MM-dd"; //
	public static final String DB_TIME_PATTERN_F1 = "yyyy/MM/dd HH:mm:ss"; //
	public static final String DB_TIME_PATTERN_F2 = "yyyy-MM-dd HH:mm:ss"; //
	public static final String DB_TIME_PATTERN_H1 = "yyyy/MM/dd HH:mm:ss.SSS"; //
	public static final String DB_TIME_PATTERN_H2 = "yyyy-MM-dd HH:mm:ss.SSS"; //
	public static final String DB_TIME_PATTERN_06 = "HH:mm:ss"; //
	public static final String DB_TIME_PATTERN_09 = "HH:mm:ss.SSS"; //
	public static final String DATE_FMT_40 = "yyyy"; //
	public static final String DATE_FMT_60 = "yyMMdd"; //
	public static final String DATE_FMT_61 = "yyyyMM"; //
	public static final String DATE_FMT_62 = "yyMMddHH"; //
	public static final String DATE_FMT_64 = "yyMMddHHmm"; //
	public static final String DATE_FMT_66 = "yyMMddHHmmss"; //
	public static final String DATE_FMT_69 = "yyMMddHHmmssSSS"; //
	public static final String DATE_FMT_80 = "yyyyMMdd"; //
	public static final String DATE_FMT_86 = "yyyyMMddHHmmss"; //
	public static final String DATE_FMT_89 = "yyyyMMddHHmmssSSS"; //
	public static final String DATE_FMT_04 = "HHmm"; //
	public static final String DATE_FMT_06 = "HHmmss"; //
	public static final String DATE_FMT_28 = "ddHHmmss"; //

	/**
	 * 格式化当前时间
	 * 
	 * @param sFmtTo
	 * @return
	 */
	public static String formatDate(String sFmtTo) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(sFmtTo);
			String sRet = formatter
					.format(new Date(System.currentTimeMillis()));
			return sRet;
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * 格式化给定的时间
	 * 
	 * @param sDate
	 * @param sFmtFrom
	 * @param sFmtTo
	 * @return
	 */
	public static String formatDate(String sDate, String sFmtFrom, String sFmtTo) {
		if (sDate == null || "".equals(sDate)) {
			return "";
		}
		if (sFmtFrom.equals(sFmtTo)) {
			return sDate;
		}
		try {
			SimpleDateFormat sdfFrom = new SimpleDateFormat(sFmtFrom);
			java.util.Date dt = sdfFrom.parse(sDate);
			SimpleDateFormat sdfTo = new SimpleDateFormat(sFmtTo);
			return sdfTo.format(dt);
		} catch (Exception ex) {
			return sDate;
		}
	}

	/**
	 * 计算天数
	 * 
	 * @param fromDate
	 * @param endDate
	 * @param sFmtFrom
	 * @return
	 */
	public static long countDays(String fromDate, String endDate,
			String sFmtFrom) {
		Timestamp t1 = Timestamp.valueOf(formatDate(fromDate, sFmtFrom,
				DB_TIME_PATTERN_H2));
		Timestamp t2 = Timestamp.valueOf(formatDate(endDate, sFmtFrom,
				DB_TIME_PATTERN_H2));
		return (t2.getTime() - t1.getTime()) / DAYMILLI + 1;
	}

	/**
	 * 计算小时数
	 * 
	 * @param fromDate
	 * @param endDate
	 * @param sFmtFrom
	 * @return
	 */
	public static long countHours(String fromDate, String endDate,
			String sFmtFrom) {
		Timestamp t1 = Timestamp.valueOf(formatDate(fromDate, sFmtFrom,
				DB_TIME_PATTERN_H2));
		Timestamp t2 = Timestamp.valueOf(formatDate(endDate, sFmtFrom,
				DB_TIME_PATTERN_H2));
		return (t2.getTime() - t1.getTime()) / HOURMILLI + 1;
	}

	/**
	 * 计算分钟数
	 * 
	 * @param fromDate
	 * @param endDate
	 * @param sFmtFrom
	 * @return
	 */
	public static long countMinutes(String fromDate, String endDate,
			String sFmtFrom) {
		Timestamp t1 = Timestamp.valueOf(formatDate(fromDate, sFmtFrom,
				DB_TIME_PATTERN_H2));
		Timestamp t2 = Timestamp.valueOf(formatDate(endDate, sFmtFrom,
				DB_TIME_PATTERN_H2));
		return (t2.getTime() - t1.getTime()) / MINUTEMILLI + 1;
	}

	/**
	 * 计算秒数
	 * 
	 * @param fromDate
	 * @param endDate
	 * @param sFmtFrom
	 * @return
	 */
	public static long countSeconds(String fromDate, String endDate,
			String sFmtFrom) {
		Timestamp t1 = Timestamp.valueOf(formatDate(fromDate, sFmtFrom,
				DB_TIME_PATTERN_H2));
		Timestamp t2 = Timestamp.valueOf(formatDate(endDate, sFmtFrom,
				DB_TIME_PATTERN_H2));
		return (t2.getTime() - t1.getTime()) / SECONDMILLI + 1;
	}

	/**
	 * 计算毫秒数
	 * 
	 * @param fromDate
	 * @param endDate
	 * @param sFmtFrom
	 * @return
	 */
	public static long countMillions(String fromDate, String endDate,
			String sFmtFrom) {
		Timestamp t1 = Timestamp.valueOf(formatDate(fromDate, sFmtFrom,
				DB_TIME_PATTERN_H2));
		Timestamp t2 = Timestamp.valueOf(formatDate(endDate, sFmtFrom,
				DB_TIME_PATTERN_H2));
		return t2.getTime() - t1.getTime() + 1;
	}

	/**
	 * 当前时间加上固定的年
	 * 
	 * @param years
	 * @param sFmtTo
	 * @return
	 */
	public static String addYears(int years, String sFmtTo) {
		String date = "";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(sFmtTo);
			Calendar calendar = dateFormat.getCalendar();
			calendar.setTime(new Date(System.currentTimeMillis()));
			calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + years);
			date = dateFormat.format(calendar.getTime());
		} catch (Exception ex) {
		}
		return date;
	}

	/**
	 * 给定的时间加上固定的年
	 * 
	 * @param date
	 * @param years
	 * @param sFmtFrom
	 * @return
	 */
	public static String addYears(String date, int years, String sFmtFrom) {
		if (date == null || date.equals("")) {
			return date;
		}
		if (years == 0) {
			return date;
		}
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(sFmtFrom);
			Calendar calendar = dateFormat.getCalendar();
			calendar.setTime(dateFormat.parse(date));
			calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + years);
			date = dateFormat.format(calendar.getTime());
		} catch (Exception ex) {
		}
		return date;
	}

	/**
	 * 当前时间加上固定的月
	 * 
	 * @param months
	 * @param sFmtTo
	 * @return
	 */
	public static String addMonths(int months, String sFmtTo) {
		String date = "";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(sFmtTo);
			Calendar calendar = dateFormat.getCalendar();
			calendar.setTime(new Date(System.currentTimeMillis()));
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + months);
			date = dateFormat.format(calendar.getTime());
		} catch (Exception ex) {
		}
		return date;
	}

	/**
	 * 给定的时间加上固定的月
	 * 
	 * @param date
	 * @param months
	 * @param sFmtFrom
	 * @return
	 */
	public static String addMonths(String date, int months, String sFmtFrom) {
		if (date == null || date.equals("")) {
			return date;
		}
		if (months == 0) {
			return date;
		}
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(sFmtFrom);
			Calendar calendar = dateFormat.getCalendar();
			calendar.setTime(dateFormat.parse(date));
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + months);
			date = dateFormat.format(calendar.getTime());
		} catch (Exception ex) {
		}
		return date;
	}

	/**
	 * 当前时间加上固定的天
	 * 
	 * @param days
	 * @param sFmtTo
	 * @return
	 */
	public static String addDays(int days, String sFmtTo) {
		String date = "";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(sFmtTo);
			Calendar calendar = dateFormat.getCalendar();
			calendar.setTime(new Date(System.currentTimeMillis()));
			calendar.set(Calendar.DAY_OF_MONTH,
					calendar.get(Calendar.DAY_OF_MONTH) + days);
			date = dateFormat.format(calendar.getTime());
		} catch (Exception ex) {
		}
		return date;
	}

	/**
	 * 给定的时间加上固定的天
	 * 
	 * @param date
	 * @param days
	 * @param sFmtFrom
	 * @return
	 */
	public static String addDays(String date, int days, String sFmtFrom) {
		if ((date == null) || date.equals("")) {
			return "";
		}
		if (days == 0) {
			return date;
		}
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(sFmtFrom);
			Calendar calendar = dateFormat.getCalendar();
			calendar.setTime(dateFormat.parse(date));
			calendar.set(Calendar.DAY_OF_MONTH,
					calendar.get(Calendar.DAY_OF_MONTH) + days);
			date = dateFormat.format(calendar.getTime());
		} catch (Exception ex) {
		}
		return date;
	}

	/**
	 * 给定的时间加上固定的小时
	 * 
	 * @param date
	 * @param hours
	 * @param sFmtFrom
	 * @return
	 */
	public static String addHours(String date, int hours, String sFmtFrom) {
		if ((date == null) || date.equals("")) {
			return "";
		}
		if (hours == 0) {
			return date;
		}
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(sFmtFrom);
			Calendar calendar = dateFormat.getCalendar();
			calendar.setTime(dateFormat.parse(date));
			calendar.set(Calendar.HOUR_OF_DAY,
					calendar.get(Calendar.HOUR_OF_DAY) + hours);
			date = dateFormat.format(calendar.getTime());
		} catch (Exception ex) {
		}
		return date;
	}

	/**
	 * 给定的时间加上固定的分钟
	 * 
	 * @param date
	 * @param minutes
	 * @param sFmtFrom
	 * @return
	 */
	public static String addMinutes(String date, int minutes, String sFmtFrom) {
		if ((date == null) || date.equals("")) {
			return "";
		}
		if (minutes == 0) {
			return date;
		}
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(sFmtFrom);
			Calendar calendar = dateFormat.getCalendar();
			calendar.setTime(dateFormat.parse(date));
			calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE)
					+ minutes);
			date = dateFormat.format(calendar.getTime());
		} catch (Exception ex) {
		}
		return date;
	}

	public static String addSeconds(String date, int seconds, String sFmtFrom) {
		if ((date == null) || date.equals("")) {
			return "";
		}
		if (seconds == 0) {
			return date;
		}
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(sFmtFrom);
			Calendar calendar = dateFormat.getCalendar();
			calendar.setTime(dateFormat.parse(date));
			calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND)
					+ seconds);
			date = dateFormat.format(calendar.getTime());
		} catch (Exception ex) {
		}
		return date;
	}

	/**
	 * 返回一周的第几天
	 * 
	 * @return 1-7的一个数、如果失败则返回-1
	 */
	public static int getDayOFWeak(String date, String sFmtFrom) {
		if ((date == null) || (date.equals(""))) {
			return -1;
		}
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(sFmtFrom);
			Calendar calendar = dateFormat.getCalendar();
			calendar.setTime(dateFormat.parse(date));
			return calendar.get(Calendar.DAY_OF_WEEK);
		} catch (Exception ex) {
			return -1;
		}
	}

	/**
	 * 返回一年的第几周
	 * 
	 * @return 1-52的一个数、如果失败返回-1
	 */
	public static int getWeekOfYear(String date, String sFmtFrom) {
		if ((date == null) || date.equals("")) {
			return -1;
		}
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(sFmtFrom);
			Calendar calendar = dateFormat.getCalendar();
			calendar.setTime(dateFormat.parse(date));
			return calendar.get(Calendar.WEEK_OF_YEAR);
		} catch (Exception ex) {
			return -1;
		}
	}
	
	/**
	 * 返回date对应的周一的年月日
	 * 
	 * @return yyyy-MM-dd格式日期
	 */
	public static String getMondayOfDate(java.util.Date time) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(time);  

        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  
        if(1 == dayWeek) {  
          cal.add(Calendar.DAY_OF_MONTH, -1);  
        }  

        // System.out.println("要计算日期为:"+sdf.format(cal.getTime())); //输出要计算日期  
        
        //设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一 
        cal.setFirstDayOfWeek(Calendar.MONDAY); 
        //获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);  
        //根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day);   

        return sdf.format(cal.getTime()); //周一时间 
   } 
	/**
	 * 返回一个月的天数
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getMonthLength(int year, int month) {
		if (month == 2) {
			if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
				return 29;
			} else {
				return 28;
			}
		}
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		} else {
			return 31;
		}
	}

	/**
	 * 返回上个月的最后一天
	 * 
	 * @param date61
	 * @return DATE_FMT_80
	 */
	public static String getLastMonEndDay(String date61) {
		return addDays(date61 + "01", -1, DATE_FMT_80);
	}

	/**
	 * 返回下个月的头一天
	 * 
	 * @param date61
	 * @return DATE_FMT_80
	 */
	public static String getNextMonBeginDay(String date61) {
		return addMonths(date61, 1, DATE_FMT_61) + "01";
	}

	/**
	 * 取得一年的月份列表
	 * 
	 * @return [1.2.3......12]
	 */
	public static List<String> getYearMonthList() {
		List<String> monthList = new ArrayList<String>();
		for (int i = 1; i < 13; i++) {
			monthList.add(String.valueOf(i));
		}
		return monthList;
	}

	/**
	 * 取得当前日期的某个位置的值，如年、月、日等等
	 * 
	 * @param descFmt
	 * @return
	 */
	public static int getSingleTime(String descFmt) {
		Calendar instance = Calendar.getInstance();
		if (descFmt.equals("Y")) {
			return instance.get(Calendar.YEAR);
		} else if (descFmt.equals("M")) {
			return instance.get(Calendar.MONTH) + 1;
		} else if (descFmt.equals("dd")) {
			return instance.get(Calendar.DAY_OF_MONTH);
		} else if (descFmt.equals("d")) {
			return instance.get(Calendar.DAY_OF_WEEK);
		} else if (descFmt.equals("HH")) {
			return instance.get(Calendar.HOUR_OF_DAY);
		} else if (descFmt.equals("H")) {
			return instance.get(Calendar.HOUR);
		} else if (descFmt.equals("m")) {
			return instance.get(Calendar.MINUTE);
		} else if (descFmt.equals("s")) {
			return instance.get(Calendar.SECOND);
		} else if (descFmt.equals("S")) {
			return instance.get(Calendar.MILLISECOND);
		}
		return 0;
	}

	public static int getSingleTime(String date, String sFmtFrom, String descFmt) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(sFmtFrom);
		Calendar instance = dateFormat.getCalendar();
		try {
			instance.setTime(dateFormat.parse(date));
		} catch (Exception ex) {
			return 0;
		}
		if (descFmt.equals("Y")) {
			return instance.get(Calendar.YEAR);
		} else if (descFmt.equals("M")) {
			return instance.get(Calendar.MONTH) + 1;
		} else if (descFmt.equals("dd")) {
			return instance.get(Calendar.DAY_OF_MONTH);
		} else if (descFmt.equals("d")) {
			return instance.get(Calendar.DAY_OF_WEEK);
		} else if (descFmt.equals("HH")) {
			return instance.get(Calendar.HOUR_OF_DAY);
		} else if (descFmt.equals("H")) {
			return instance.get(Calendar.HOUR);
		} else if (descFmt.equals("m")) {
			return instance.get(Calendar.MINUTE);
		} else if (descFmt.equals("s")) {
			return instance.get(Calendar.SECOND);
		} else if (descFmt.equals("S")) {
			return instance.get(Calendar.MILLISECOND);
		}
		return 0;
	}

	/**
	 * 判断是否为闰年
	 * 
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		if (year % 100 != 0 && year % 4 == 0) {
			return true;
		} else if (year % 400 == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 连接年月日形成日期形式
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param partten
	 * @return
	 */
	public static String dateJoin(String year, String month, String day,
			String partten) {
		String date = (year == null) ? "" : year;
		if (month != null) {
			date = date + partten
					+ ((month.length() == 1) ? "0" + month : month);
			if (day != null) {
				date = date + partten + ((day.length() == 1) ? "0" + day : day);
			}
		}
		return date;
	}

	/**
	 * 判断给定的日期是否在范围内，前闭后开
	 * 
	 * @param date
	 * @param fromDate
	 * @param endDate
	 * @return
	 */
	public static boolean dateBetween(String date, String fromDate,
			String endDate) {
		if(fromDate == null || endDate == null) {
			return true;
		}
		
		if (date.compareTo(fromDate) < 0) {
			return false;
		}
		if (date.compareTo(endDate) >= 0) {
			return false;
		}
		return true;
	}

	/**
	 * 时间差，返回字串格式
	 * 
	 * @param fromDate
	 * @param endDate
	 * @param sFmtFrom
	 * @return ?d?h?min?s
	 */
	public static String dateDiff(String fromDate, String endDate,
			String sFmtFrom) {
		Timestamp t1 = Timestamp.valueOf(formatDate(fromDate, sFmtFrom,
				DB_TIME_PATTERN_H2));
		Timestamp t2 = Timestamp.valueOf(formatDate(endDate, sFmtFrom,
				DB_TIME_PATTERN_H2));
		long between = (t2.getTime() - t1.getTime());
		long day = between / DAYMILLI;
		long hour = between % DAYMILLI / HOURMILLI;
		long minute = between % HOURMILLI / MINUTEMILLI;
		long second = between % MINUTEMILLI / SECONDMILLI;
		return day + "d" + hour + "h" + minute + "min" + second + "s";
	}

	/**
	 * 按照指定的时间格式来格式化当前时间
	 * 
	 * @param date
	 *            待格式化的时间 (yyyy-mm-dd hh:mm:ss.fffffffff)
	 * @param sFmtTo
	 *            时间格式
	 * @return 当前时间
	 */
	public static String getFormatTime(Timestamp date, String sFmtTo) {
		if (date == null) {
			return null;
		}
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(sFmtTo);
			String sRet = formatter.format(date);
			return sRet;
		} catch (Exception ex) {
			return "";
		}
	}
	/**
     * 获取当前时间
     * 
     * @return Timestamp
     */
	public static Timestamp getTimestampNow() { 
	    return new Timestamp(System.currentTimeMillis());
	}
	/**
	 * 按照指定的格式来格式化当前的时间
	 * 
	 * @return Timestamp
	 */
	public static Timestamp toSqlTimestamp() {
		return Timestamp.valueOf(formatDate(DB_TIME_PATTERN_F2));
	}

	/**
	 * 按照指定的格式来格式化指定的时间
	 * 
	 * @param date
	 *            待格式化的时间
	 * @param sFmtFrom
	 *            时间格式
	 * @return Timestamp
	 */
	public static Timestamp toSqlTimestamp(String date, String sFmtFrom) {
		if (date == null || "".equals(date)) {
			return null;
		}
		try {
			return Timestamp.valueOf(formatDate(date, sFmtFrom,
					DB_TIME_PATTERN_F2));
		} catch (Exception ex) {
			return null;
		}
	}

	//获取某年某月的第一天日期
	public static java.util.Date getStartMonthDate(int year, int month) {
	          Calendar calendar = Calendar.getInstance();
	          calendar.set(year, month - 1, 1);
	          return calendar.getTime();
	}
	
	public static void main(String[] args) throws ParseException {
		// System.out.println(TimeUtils.addYears("2013-03-01", -2,
		// TimeUtils.DB_DATE_PATTERN_82));
		// System.out.println(TimeUtils.dateDiff("2012-09-18 10:11:23",
		// "2012-09-19 10:13:48", TimeUtils.DB_TIME_PATTERN_F2));
		// System.out.println(TimeUtils.getSingleTime("2016-05-08",
		// TimeUtils.DB_DATE_PATTERN_82, "dd"));
		//System.out.println("2016-05-20".compareTo("2016-05-10 10:10:10"));
		//System.out.println("2016-05-20".compareTo("2016-05-30 10:10:10"));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		java.util.Date date = sdf.parse("2017-07-25");  
	    
		System.out.println(getMondayOfDate(date));
	}
}