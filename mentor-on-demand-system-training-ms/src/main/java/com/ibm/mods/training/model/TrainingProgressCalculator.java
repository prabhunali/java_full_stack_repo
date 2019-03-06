package com.ibm.mods.training.model;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.ibm.mods.training.util.TrainingUtil.*;

public class TrainingProgressCalculator {
	
	private Date startDate;
	private Date endDate;
	private String startTime;
	private String endTime;
	private String daysOfSession;
	private double previousProgress;
	
	public TrainingProgressCalculator(Date startDate, Date endDate, String startTime, String endTime, String daysOfSession, double previousProgress) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.daysOfSession = daysOfSession;
		this.previousProgress = previousProgress;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDaysOfSession() {
		return daysOfSession;
	}

	public void setDaysOfSession(String daysOfSession) {
		this.daysOfSession = daysOfSession;
	}

	public double getPreviousProgress() {
		return previousProgress;
	}

	public void setPreviousProgress(double previousProgress) {
		this.previousProgress = previousProgress;
	}
	
	public int getTotalNumberOfSessions() {
		
		Date current = this.startDate;
		
		List<String> sessionDays = Arrays.asList(this.daysOfSession.split(","));
		int totalNoOfSessions = 0;
		
		while(!current.after(endDate)) {
			String day = dateToString(DAY_FORMAT, current);
			if(sessionDays.contains(day)) {
				totalNoOfSessions++;
			}
			
			Calendar calendar = Calendar.getInstance();
	        calendar.setTime(current);
	        calendar.add(Calendar.DATE, 1);
	        current = calendar.getTime();
		}
		
		return totalNoOfSessions;
	}
	
	public double getHoursPerSession() throws ParseException {
		return getHoursDiff(this.startTime, this.endTime);
	}
	
	public double getTrainingTotalHours() throws ParseException {
		return (getTotalNumberOfSessions() * getHoursPerSession());
	}
	
	public double getProgressPerSession() throws ParseException {
		double progressPerSession = (getHoursPerSession() / getTrainingTotalHours()) * 100;
		return progressPerSession;
	}
	
	public double getCurrentProgress() throws ParseException {
		return this.previousProgress + getProgressPerSession();
 	}

}
