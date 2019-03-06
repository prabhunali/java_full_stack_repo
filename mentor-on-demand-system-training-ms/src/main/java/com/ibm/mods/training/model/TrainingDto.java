package com.ibm.mods.training.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TrainingDto {

	private long id;
	private long userId;
	private long mentorId;
	private long mentorSkillId;
	private long skillId;
	private String status;
	private double progress;
	private int rating;
	private Date startDate;
	private Date endDate;
	private Date startTime;
	private Date endTime;
	private String daysOfSession;
	private double trainingTotalHours;
	private double trainingTotalAmount;
	
	public TrainingDto() {
		
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public long getMentorId() {
		return mentorId;
	}
	
	public void setMentorId(long mentorId) {
		this.mentorId = mentorId;
	}
	
	public long getMentorSkillId() {
		return mentorSkillId;
	}

	public void setMentorSkillId(long mentorSkillId) {
		this.mentorSkillId = mentorSkillId;
	}

	public long getSkillId() {
		return skillId;
	}
	
	public void setSkillId(long skillId) {
		this.skillId = skillId;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public double getProgress() {
		return progress;
	}
	
	public void setProgress(double progress) {
		this.progress = progress;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
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
	
	public Date getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getDaysOfSession() {
		return daysOfSession;
	}

	public void setDaysOfSession(String daysOfSession) {
		this.daysOfSession = daysOfSession;
	}

	public double getTrainingTotalHours() {
		return trainingTotalHours;
	}

	public void setTrainingTotalHours(double trainingTotalHours) {
		this.trainingTotalHours = trainingTotalHours;
	}

	public double getTrainingTotalAmount() {
		return trainingTotalAmount;
	}

	public void setTrainingTotalAmount(double trainingTotalAmount) {
		this.trainingTotalAmount = trainingTotalAmount;
	}

}
