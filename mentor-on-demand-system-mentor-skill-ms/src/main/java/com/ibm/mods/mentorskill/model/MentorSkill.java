package com.ibm.mods.mentorskill.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mentor_skills")
public class MentorSkill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "mentor_id")
	private long mentorId;
	
	@Column(name = "skill_id")
	private long skillId;
	
	@Column(name = "self_rating")
	private int selfRating;
	
	@Column(name = "years_of_exp")
	private int yearOfExperience;
	
	@Column(name = "facilities_offered")
	private String facilitiesOffered;

	@Column(name = "hourly_rate")
	private double hourlyRate;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public int getSelfRating() {
		return selfRating;
	}

	public void setSelfRating(int selfRating) {
		this.selfRating = selfRating;
	}

	public int getYearOfExperience() {
		return yearOfExperience;
	}

	public void setYearOfExperience(int yearOfExperience) {
		this.yearOfExperience = yearOfExperience;
	}

	public String getFacilitiesOffered() {
		return facilitiesOffered;
	}

	public void setFacilitiesOffered(String facilitiesOffered) {
		this.facilitiesOffered = facilitiesOffered;
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

}
