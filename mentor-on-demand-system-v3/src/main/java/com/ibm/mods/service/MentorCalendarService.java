package com.ibm.mods.service;

import java.util.List;

import com.ibm.mods.mentorcal.model.MentorCalendar;

public interface MentorCalendarService {

	List<MentorCalendar> getMentorCalendars(long id, String timeFrom, String timeTo);
	List<MentorCalendar> getMentorCalendars(long mentorId);
	List<MentorCalendar> getMentorCalendars(List<Long> skillIds, String timeFrom, String timeTo);
	void saveMentorCalendars(List<MentorCalendar> mentorCalendars);
	List<MentorCalendar> getMentorCalendarsByMentorSkillId(long mentorSkillId);
	void saveMentorCalendar(MentorCalendar calendar);
	void updateMentorCalendar(MentorCalendar calendar);
	void deleteMentorCalendar(long id);
}
