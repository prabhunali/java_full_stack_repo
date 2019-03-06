package com.ibm.mods.mentorskill.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author JakeAGUSTIN
 *
 */
@Entity
@Table(name = "trainings")
public class Training {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "mentor_id")
	private long mentorId;
	
	@Column(name = "mentor_skill_id")
	private long mentorSkillId;
	
	@Column(name = "skill_id")
	private long skillId;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "progress")
	private double progress;
	
	@Column(name = "rating")
	private int rating;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	@Column(name = "start_time")
	private Date startTime;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	@Temporal(TemporalType.TIME)
	@Column(name = "end_time")
	private Date endTime;
	
	@Column(name = "days_of_session")
	private String daysOfSession;
	
	@Column(name = "tr_total_hrs")
	private double trainingTotalHours;
	
	@Column(name = "tr_total_amount")
	private double trainingTotalAmount;
	
	public Training() {
		
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
