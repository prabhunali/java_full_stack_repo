package com.ibm.mods.mentor.model;

import java.util.List;

import com.ibm.mods.mentorcal.model.MentorCalendar;
import com.ibm.mods.mentorskill.model.MentorSkill;
import com.ibm.mods.skill.model.Skill;

public class MentorSearchResult {

	private Mentor mentor;
	private Skill skill;
	private MentorSkill mentorSkill;
	private List<MentorCalendar> mentorCalendars;
	private String mentorFirstName;
	private String mentorLastName;
	private String contactNo;
	
	public MentorSearchResult() {
		
	}
	
	public MentorSearchResult(Mentor mentor, Skill skill, MentorSkill mentorSkill, List<MentorCalendar> mentorCalendars, String firstname, String lastname, String contactNo) {
		this.mentor = mentor;
		this.skill = skill;
		this.mentorSkill = mentorSkill;
		this.mentorCalendars = mentorCalendars;
		this.mentorFirstName = firstname;
		this.mentorLastName = lastname;
		this.contactNo = contactNo;
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
	
}
