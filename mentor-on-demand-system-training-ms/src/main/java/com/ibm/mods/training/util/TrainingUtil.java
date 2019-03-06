package com.ibm.mods.training.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TrainingUtil {
	
	public static final String SQL_DATE_FORMAT = "yyyy-MM-dd";
	public static final String SQL_TIME_FORMAT = "HH:mm:ss";
	public static final String SQL_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DAY_FORMAT	   = "E";
	
	public static double getHoursDiff(String startTime, String endTime) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date from = format.parse(startTime);
		Date to = format.parse(endTime);
		long difference = to.getTime() - from.getTime();
		
		return milliSecondsToHours(difference);
	}
	
	public static double milliSecondsToHours(long milliSeconds) {
		return ((Long.valueOf(milliSeconds).doubleValue() /1000) / 60 / 60);
	}
	
	public static String dateToString(String pattern, Date date) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
	public static Date stringToDate(String pattern, String strDate) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.parse(strDate);
	}
	
	public static boolean todayTimeIsAfter(Date givenDate) throws ParseException {
		Date today = Calendar.getInstance().getTime();
		String timeToday = dateToString(SQL_TIME_FORMAT, today);
    	Calendar todayCalendar = Calendar.getInstance();
    	todayCalendar.setTime(stringToDate(SQL_TIME_FORMAT, timeToday));
    	
    	Calendar calendar = Calendar.getInstance();
    	String time = dateToString(SQL_TIME_FORMAT, givenDate);
    	calendar.setTime(stringToDate(SQL_TIME_FORMAT, time));
    	
    	System.out.println("Today: " + todayCalendar.getTime());
    	System.out.println("Given Date: " + calendar.getTime());
    	
    	// Compare time regardless of Date
    	return (todayCalendar.getTime().after(calendar.getTime()));
	}
	
}
