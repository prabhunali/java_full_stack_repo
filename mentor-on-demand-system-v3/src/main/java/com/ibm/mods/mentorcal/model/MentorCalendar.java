package com.ibm.mods.mentorcal.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mentor_calendar")
public class MentorCalendar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "mentor_skill_id")
	private long mentorSkillId;
	
	@Column(name = "mentor_id")
	private long mentorId;
	
	@Column(name = "skill_id")
	private long skillId;
	
	//@Temporal(TemporalType.TIME)
	@Column(name = "start_time")
	private String startTime;
	
	//@Temporal(TemporalType.TIME)
	@Column(name = "end_time")
	private String endTime;
	
//	@Temporal(TemporalType.DATE)
//	@Column(name = "start_date")
//	private Date startDate;
//	
//	@Temporal(TemporalType.DATE)
//	@Column(name = "end_date")
//	private Date endDate;

	@Column(name="days_available")
	private String daysAvailable;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMentorSkillId() {
		return mentorSkillId;
	}

	public void setMentorSkillId(long mentorSkillId) {
		this.mentorSkillId = mentorSkillId;
	}

	public long getMentorId() {
		return mentorId;
	}

	public void setMentorId(long mentorId) {
		this.mentorId = mentorId;
	}

	public long getSkillId() {
		return skillId;
	}

	public void setSkillId(long skillId) {
		this.skillId = skillId;
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

	public String getDaysAvailable() {
		return daysAvailable;
	}

	public void setDaysAvailable(String daysAvailable) {
		this.daysAvailable = daysAvailable;
	}
	
	public String[] getDaysAvailableAsArray() {
		return this.getDaysAvailable().split(",");
	}

}
