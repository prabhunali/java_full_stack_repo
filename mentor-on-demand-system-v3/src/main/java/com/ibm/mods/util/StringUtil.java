package com.ibm.mods.util;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class StringUtil {

	// (days_available LIKE '%MON%' AND days_available LIKE '%TUE%')
	public static String generateLikeDaysString(List<String> days) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("( ");
		
		for(int index = 0; index < days.size(); index++) {
			sb.append("days_available LIKE ")
			.append("'%").append(days.get(index)).append("%' ");
	
			if(index < (days.size() - 1)) {
				sb.append("AND ");
			}
		}
		sb.append(" )");
		
		return sb.toString();
	}
	
	public static void main(String[] args) throws ParseException {
		Date dateFrom = DateUtil.stringToDate(DateUtil.DATE_TIME_PATTERN, "19-01-2019 01:07 AM");
		Date dateTo = DateUtil.stringToDate(DateUtil.DATE_TIME_PATTERN, "21-01-2019 01:07 AM");
		
		List<String> days = DateUtil.getAvailabilityDays(dateFrom, dateTo);
		
		System.out.println(generateLikeDaysString(days));
	}

}
