package com.ibm.mods.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.mods.mentorcal.model.MentorCalendar;
import com.ibm.mods.service.MentorCalendarService;
import com.ibm.mods.user.auth.model.ApiResponse;
import com.ibm.mods.util.URL;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MentorCalendarController {

	@Autowired
	private MentorCalendarService mentorCalService;
	
	@GetMapping(URL.MENTOR_CAL_BY_MENTOR_ID)
	public List<MentorCalendar> getMentorCalendars(@RequestParam long mentorId) {
		return this.mentorCalService.getMentorCalendars(mentorId);
	}
	
	@PostMapping(URL.MENTOR_CAL_SAVE)
	public ApiResponse<Void> saveMentorCalendar(@RequestBody MentorCalendar calendar) {
		mentorCalService.saveMentorCalendar(calendar);
		return new ApiResponse<Void>(HttpStatus.OK.value(), "Mentor Calendar successfuly added.", null);
	}
	
	@PutMapping(URL.MENTOR_CAL_UPDATE)
	public ApiResponse<Void> updateMentorCalendar(@RequestBody MentorCalendar calendar) {
		mentorCalService.updateMentorCalendar(calendar);
		return new ApiResponse<>(HttpStatus.OK.value(), "Mentor Calendar successfully updated.", null);
	}
	
	@DeleteMapping(URL.MENTOR_CAL_DELETE)
	public ApiResponse<Void> deleteMentorCalendar(@PathVariable long id) {
		mentorCalService.deleteMentorCalendar(id);
		return new ApiResponse<>(HttpStatus.OK.value(), "Mentor Skill successfully deleted.", null);
	}
	
}
