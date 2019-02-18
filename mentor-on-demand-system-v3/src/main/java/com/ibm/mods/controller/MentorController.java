package com.ibm.mods.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.mods.mentor.model.Mentor;
import com.ibm.mods.mentor.model.MentorDto;
import com.ibm.mods.mentor.model.MentorSearchResult;
import com.ibm.mods.mentorcal.model.MentorCalendar;
import com.ibm.mods.mentorskill.model.MentorSkill;
import com.ibm.mods.service.MentorCalendarService;
import com.ibm.mods.service.MentorService;
import com.ibm.mods.service.MentorSkillService;
import com.ibm.mods.service.SkillService;
import com.ibm.mods.service.UserService;
import com.ibm.mods.skill.model.Skill;
import com.ibm.mods.user.model.User;
import com.ibm.mods.util.URL;
import static com.ibm.mods.util.DateUtil.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MentorController {
	
	@Autowired
	private MentorService mentorService;
	
	@Autowired
	private MentorSkillService mentorSkillService;
	
	@Autowired
	private SkillService skillService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MentorCalendarService mentorCalendarService;
	
	@PostMapping(URL.MENTOR_SAVE)
	public Mentor save(@RequestBody MentorDto mentor) {
		return mentorService.save(mentor);
	}
	
	@PutMapping(URL.MENTOR_UPDATE)
	public MentorDto update(@RequestBody MentorDto mentorDto) {
		return mentorService.update(mentorDto);
	}
	
	
	@GetMapping(URL.HOME_SEARCH_MENTOR)
	public List<MentorSearchResult> searchMentors(@RequestParam String skillName
												, @RequestParam String dateFromTo
												, @RequestParam String startTime
												, @RequestParam String endTime) throws ParseException {
		
		List<MentorSearchResult> results = new ArrayList<>();
		
		// Search skill name from Skill table
		List<Skill> skillResults = skillService.getSkills(skillName);
		
		if(skillResults.size() > 0 && !skillResults.equals(null)) {
			
			List<Long> skillIds = skillResults.stream().map(Skill::getId).collect(Collectors.toList());
			
			// Parse Date (From - To) from client
			// Sample Date Received from Client: "01/01/2019 - 02/01/2019"
			Date dateFrom = stringToDate(DATE_PATTERN_CLIENT, dateFromTo.split("-")[0].trim());
			Date dateTo = stringToDate(DATE_PATTERN_CLIENT, dateFromTo.split("-")[1].trim());
			
			List<MentorSkill> mentorSkills = mentorSkillService.findAllBySkillId(skillIds);
			if(mentorSkills.size() > 0 && !mentorSkills.equals(null)) {
				for(MentorSkill mentorSkill : mentorSkills) {
					
					// Check if mentor is active (Exclude if inactive
					User user = userService.findById(mentorSkill.getMentorId());
					if(user.equals(null) || !user.isActive()) {
						continue;
					}
					
					// Get mentor calendars with such mentor skill id and given time slot
					List<MentorCalendar> mentorCalendars = mentorCalendarService.getMentorCalendars(mentorSkill.getId(), startTime, endTime);
					
					if(mentorCalendars.size() > 0 && !mentorCalendars.equals(null)) {
						//for(MentorCalendar mc: mentorCalendars) {
						for(Iterator<MentorCalendar> mc = mentorCalendars.iterator(); mc.hasNext();) {
							MentorCalendar mentorCalendar = mc.next();
							// Check if Mentor's Days Availability Matches Time Slot entered by User
							List<String> mentorAvailableDays = Arrays.asList(mentorCalendar.getDaysAvailable().split(","));
							List<String> searchedAvailableDays = getAvailabilityDays(dateFrom, dateTo);
							
							if(!mentorAvailableDays.containsAll(searchedAvailableDays)) {
								mc.remove();
							}
						}
					} else {
						continue;
					}
					
					// Re-check if there are still remaining mentor calendars that passed the search criteria
					if(mentorCalendars.size() <= 0 || mentorCalendars.equals(null)) {
						continue;
					}
					
					
					Skill skill = skillService.getSkill(mentorSkill.getSkillId());
					Mentor mentor = mentorService.findById(mentorSkill.getMentorId());
					
					results.add(new MentorSearchResult(mentor
													 , skill
													 , mentorSkill
													 , mentorCalendars
													 , user.getFirstName()
													 , user.getLastName()
													 , user.getContactNumber()));
				}
			}
		} else {
			// TODO
		}
		
		return results;
	}
	
	public MentorSearchResult getMentorSearchProfile(@RequestParam long mentorSkillId) {
		// Get Mentor Skill by id
		// Get Skill by skill_id
		// Get Mentor Calendar by mentor_skill_id
		// Get mentor by mentor_id
		// Get user firstname, lastname, contactNo, and email????
//		MentorSkill mentorSkill = mentorSkillService.findMentorSkillById(mentorSkillId);
//		Skill skill = skillService.getSkill(mentorSkill.getSkillId());
//		List<MentorCalendar> mentorCal = mentorCalendarService.getMentorCalendarsByMentorSkillId(mentorSkillId);
//		Mentor mentor = mentorService.findById(mentorSkill.getMentorId());
//		User user = userService.findById(mentorSkill.getMentorId());
//		
//		MentorSearchResult result = new MentorSearchResult(mentor, skill, mentorSkill, mentorCalendar, firstname, lastname, contactNo)
		return null;
	}

}
