package com.ibm.mods.mentorskill.model;

import java.util.List;
import java.util.Map;

import com.ibm.mods.mentorskill.model.MentorSkill;

public class MentorSearchResult {

	private Mentor mentor;
	private Skill skill;
	private MentorSkill mentorSkill;
	private List<MentorCalendar> mentorCalendars;
	private String mentorFirstName;
	private String mentorLastName;
	private String contactNo;
	private Map<String, List<Training>> trainings;
	//private List<Training> trainings;
	
	public MentorSearchResult() {
		
	}
	
	public MentorSearchResult(Mentor mentor, Skill skill, MentorSkill mentorSkill, List<MentorCalendar> mentorCalendars, String firstname, String lastname, String contactNo, Map<String, List<Training>> trainings) {
		this.mentor = mentor;
		this.skill = skill;
		this.mentorSkill = mentorSkill;
		this.mentorCalendars = mentorCalendars;
		this.mentorFirstName = firstname;
		this.mentorLastName = lastname;
		this.contactNo = contactNo;
		this.trainings = trainings;
	}
	
	public Mentor getMentor() {
		return mentor;
	}

	public void setMentor(Mentor mentor) {
		this.mentor = mentor;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public MentorSkill getMentorSkill() {
		return mentorSkill;
	}

	public void setMentorSkill(MentorSkill mentorSkill) {
		this.mentorSkill = mentorSkill;
	}
	
	public List<MentorCalendar> getMentorCalendars() {
		return mentorCalendars;
	}

	public void setMentorCalendars(List<MentorCalendar> mentorCalendars) {
		this.mentorCalendars = mentorCalendars;
	}

	public String getMentorFirstName() {
		return mentorFirstName;
	}
	
	public void setMentorFirstName(String mentorFirstName) {
		this.mentorFirstName = mentorFirstName;
	}
	
	public String getMentorLastName() {
		return mentorLastName;
	}
	
	public void setMentorLastName(String mentorLastName) {
		this.mentorLastName = mentorLastName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Map<String, List<Training>>getTrainings() {
		return trainings;
	}

	public void setTrainings(Map<String, List<Training>> trainings) {
		this.trainings = trainings;
	}
	
}
