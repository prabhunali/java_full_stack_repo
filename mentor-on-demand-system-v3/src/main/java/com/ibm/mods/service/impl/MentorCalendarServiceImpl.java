package com.ibm.mods.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.mods.mentorcal.model.MentorCalendar;
import com.ibm.mods.repository.MentorCalendarRepository;
import com.ibm.mods.service.MentorCalendarService;

@Service(value = "mentorCalendarService")
public class MentorCalendarServiceImpl implements MentorCalendarService {

	@Autowired
	private MentorCalendarRepository mentorCalRepository;

	@Override
	public List<MentorCalendar> getMentorCalendars(long id, String timeFrom, String timeTo) {
		List<MentorCalendar> mentorCalendars = mentorCalRepository.getMentorCalendars(id, timeFrom, timeTo);
		if(mentorCalendars.size() <= 0 || mentorCalendars.equals(null)) {
			return new ArrayList<MentorCalendar>();
		}
		return mentorCalendars;
	}

	@Override
	public List<MentorCalendar> getMentorCalendars(long mentorId) {
		return mentorCalRepository.findByMentorId(mentorId);
	}

	@Override
	public void saveMentorCalendars(List<MentorCalendar> mentorCalendars) {
		mentorCalRepository.saveAll(mentorCalendars);
	}

	@Override
	public List<MentorCalendar> getMentorCalendars(List<Long> skillIds, String timeFrom, String timeTo) {
		return mentorCalRepository.getMentorCalendars(skillIds, timeFrom, timeTo);
	}

	@Override
	public List<MentorCalendar> getMentorCalendarsByMentorSkillId(long mentorSkillId) {
		return mentorCalRepository.findByMentorSkillId(mentorSkillId);
	}

	@Override
	public void saveMentorCalendar(MentorCalendar calendar) {
		mentorCalRepository.save(calendar);
	}

	@Override
	public void updateMentorCalendar(MentorCalendar calendar) {
		mentorCalRepository.save(calendar);
	}

	@Override
	public void deleteMentorCalendar(long id) {
		mentorCalRepository.deleteById(id);
	}

}
