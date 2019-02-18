package com.ibm.mods.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

	public static final String DATE_PATTERN_DB = "yyyy-MM-dd";
	public static final String DATE_PATTERN_CLIENT	= "MM/dd/yyyy";
	public static final String TIME_PATTERN_24HR_FMT = "HH:mm";
	public static final String TIME_PATTERN_12HR_FMT = "hh:mm a";
	public static final String DATE_TIME_PATTERN	= "dd-MM-yyyy hh:mm a";
	public static final String DAY_PATTERN			= "E";
	
	public static Date stringToDate(String pattern, String strDate) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.parse(strDate);
	}
	
	public static String dateToString(String pattern, Date date) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
	public static String localDateToString(String pattern, LocalDate localDate) {
		return localDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_DB));
	}
	
	public static String stringDateToString(String pattern, String strDate) throws ParseException {
		Date date = stringToDate(DATE_TIME_PATTERN, strDate);
		return dateToString(pattern, date);
		
	}
	
	public static List<String> getAvailabilityDays(Date start, Date end) {
		Date current = start;
		List<String> days = new ArrayList<String>();
	    while (!current.after(end)) {
	    	String day = dateToString(DAY_PATTERN, current);
	    	if(!days.contains(day)) {
	    		days.add(day);
	    	}
	    	
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(current);
	        calendar.add(Calendar.DATE, 1);
	        current = calendar.getTime();
	    }
	    return days;
	}
	
//	static void iterateBetweenDatesJava7(Date start, Date end) {
//	    Date current = start;
//	 
//	    while (!current.after(end)) {
//	        System.out.println(current);
//	 
//	        Calendar calendar = Calendar.getInstance();
//	        calendar.setTime(current);
//	        calendar.add(Calendar.DATE, 1);
//	        current = calendar.getTime();
//	    }
//	}
	
//	public static String subStrDate() {
//		
//	}
//	
//	public static String subStrTime() {
//		
//	}
	
	public static void main(String[] args) throws ParseException {
//		Date date = stringToDate("dd-MM-yyyy hh:mm a", "19-01-2019 01:07 AM");
//		System.out.println(date);
//		String dateFromStr = stringDateToString(DATE_PATTERN, "28-01-2019 01:07 PM");
//		String dateToStr = stringDateToString(DATE_PATTERN, "31-01-2019 01:07 PM");
//		
//		String timeFrom24 = stringDateToString(TIME_PATTERN_24HR_FMT, "19-01-2019 01:07 PM");
//		String timeFrom12 = stringDateToString(TIME_PATTERN_12HR_FMT, "19-01-2019 01:07 PM");
//		System.out.println(dateFromStr);
//		System.out.println(timeFrom24);
//		System.out.println(timeFrom12);
//		
		
//		Date dateFrom = stringToDate(DATE_TIME_PATTERN, "19-01-2019 01:07 AM");
//		Date dateTo = stringToDate(DATE_TIME_PATTERN, "01-02-2019 01:07 AM");

		//System.out.println(getAvailabilityDays(dateFrom, dateTo));
//		String pattern = "E MM dd yyyy HH:mm:ss";
//		System.out.println(stringDateToString(pattern, "Sun Jan 27 2019 19:44:59"));
//		System.out.println(new Date());
//		System.out.println(stringDateToString(TIME_PATTERN_24HR_FMT, "Sun Jan 27 2019 19:44:59 GMT+0800 (Philippine Standard Time)"));
//		System.out.println(stringDateToString(TIME_PATTERN_24HR_FMT, "Sun Jan 27 2019 19:44:59"));
		
		//String dateFromClient = "Sun Jan 27 2019 19:44:59 GMT+0800 (Philippine Standard Time)";
		//System.out.println(dateFromClient.substring(16, 24));
	
		Date dateFrom = stringToDate(DATE_PATTERN_CLIENT, "01/01/2019");
		Date dateTo = stringToDate(DATE_PATTERN_CLIENT, "01/06/2019");
		List<String> days_availableClient = getAvailabilityDays(dateFrom, dateTo);
		
		String days_available_db = "Tue,Wed,Thu,Sat,Fri";
		String days[] = days_available_db.split(",");
		List<String> daysList = Arrays.asList(days);
		
		System.out.println(daysList + " contains all " + days_availableClient + "?");
		
		if(daysList.containsAll(days_availableClient)) {
			System.out.println("Answer: YES");
		} else {
			System.out.println("Answer: NO");
		}
		
	}
	
	
	
}
