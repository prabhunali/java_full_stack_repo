package com.ibm.mods.mentorskill.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.mods.mentorskill.model.Mentor;
import com.ibm.mods.mentorskill.model.MentorCalendar;
import com.ibm.mods.mentorskill.model.MentorDto;
import com.ibm.mods.mentorskill.model.MentorSearchResult;
import com.ibm.mods.mentorskill.model.MentorSkill;
import com.ibm.mods.mentorskill.model.Skill;
import com.ibm.mods.mentorskill.model.Training;
import com.ibm.mods.mentorskill.security.model.User;
import com.ibm.mods.mentorskill.service.AuthenticationService;
import com.ibm.mods.mentorskill.service.ExternalApiService;
import com.ibm.mods.mentorskill.service.MentorCalendarService;
import com.ibm.mods.mentorskill.service.MentorService;
import com.ibm.mods.mentorskill.service.MentorSkillService;
import com.ibm.mods.mentorskill.service.SkillService;
import com.ibm.mods.mentorskill.util.TrainingStatus;
import com.ibm.mods.mentorskill.util.URL;

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
	private AuthenticationService userService;
	
	@Autowired
	private ExternalApiService externalApiService;
	
	@Autowired
	private MentorCalendarService mentorCalendarService;
	
	@GetMapping(URL.MENTOR)
	public Mentor getMentor(@RequestParam long mentorId) {
		return mentorService.findById(mentorId);
	}
	
	@GetMapping(URL.MENTOR_GET_BY_USER_ID)
	public Mentor getMentorByUserId(@RequestParam long userId) {
		return mentorService.findByUserId(userId);
	}
	
	@PostMapping(URL.MENTOR_SAVE)
	public Mentor saveMentor(@RequestBody MentorDto mentor) {
		return mentorService.save(mentor);
	}
	
	@PutMapping(URL.MENTOR_UPDATE)
	public MentorDto updateMentor(@RequestBody MentorDto mentorDto) {
		return mentorService.update(mentorDto);
	}
	
	@GetMapping(URL.HOME_SEARCH_MENTOR)
	public List<MentorSearchResult> searchMentors(
		    @RequestParam String skillName
		  , @RequestParam String startTime
		  , @RequestParam String endTime
		  , @RequestParam String daysOfSession
												 ) {
		List<MentorSearchResult> results = new ArrayList<>();
		
		// Search skill name from Skill table
		List<Skill> skillResults = skillService.getSkills(skillName);
		
		if(skillResults.size() > 0 && !skillResults.equals(null)) {
			List<Long> skillIds = skillResults.stream().map(Skill::getId).collect(Collectors.toList());
			
			// Get all mentor skill with such skills ids
			List<MentorSkill> mentorSkills = mentorSkillService.findAllBySkillId(skillIds);
			if(mentorSkills.size() > 0 && !mentorSkills.equals(null)) {
				
				for(MentorSkill mentorSkill : mentorSkills) {
					
					// First, check if mentor is active (Exclude if inactive)
					User user = userService.getUserById(mentorSkill.getMentorId());
					if(user.equals(null) || !user.isActive()) {
						continue;
					}
					
					// Get mentor calendars with such mentor skill id and given time slot
					List<MentorCalendar> mentorCalendars = mentorCalendarService.getMentorCalendars(mentorSkill.getId(), startTime, endTime);
					
					if(mentorCalendars.size() > 0 && !mentorCalendars.equals(null)) {
						for(Iterator<MentorCalendar> mc = mentorCalendars.iterator(); mc.hasNext();) {
							// Check if Mentor's Days Availability Matches Time Slot entered by User
							MentorCalendar mentorCalendar = mc.next();
							List<String> mentorScheduleDays = Arrays.asList(mentorCalendar.getDaysAvailable().split(","));
							List<String> userRequestDays = Arrays.asList(daysOfSession.split(","));
							
							if(!mentorScheduleDays.containsAll(userRequestDays)) {
								mc.remove();
							}
						}
					} else {continue;}
					
					// Re-check if there are still search result that passed all above criteria
					if(mentorCalendars.size() <= 0 || mentorCalendars.equals(null)) {
						continue;
					}
					
					// Create Response
					Skill skill = skillService.getSkill(mentorSkill.getSkillId());
					Mentor mentor = mentorService.findByUserId(user.getId());
					
					List<Training> completedTrainings = externalApiService.getMentorTainings(mentor.getUserId(), TrainingStatus.COMPLETED.get());
					
					results.add(new MentorSearchResult(mentor
							 , skill
							 , mentorSkill
							 , mentorCalendars
							 , user.getFirstName()
							 , user.getLastName()
							 , user.getContactNumber()
							 , groupTrainingsBySkillName(completedTrainings)));
				}
				
			} else {
				//TODO
				return new ArrayList<>(0);
			}
		} else {
			// TODO
			return new ArrayList<>(0);
		}
		
		return results;
	}
	
	@GetMapping(URL.HOME_SEARCH_MENTOR_PROFILE)
	public MentorSearchResult getMentorSearchProfile(@RequestParam long mentorSkillId) {
		MentorSkill mentorSkill = mentorSkillService.findMentorSkillById(mentorSkillId);
		if(mentorSkill == null) {
			return null;
		}
	
		Skill skill = skillService.getSkill(mentorSkill.getSkillId());
		List<MentorCalendar> mentorCalendars = mentorCalendarService.getMentorCalendarsByMentorSkillId(mentorSkillId);
		Mentor mentor = mentorService.findByUserId(mentorSkill.getMentorId());
		List<Training> trainings = externalApiService.getMentorTainings(mentor.getUserId(), TrainingStatus.COMPLETED.get());
		User user = userService.getUserById(mentor.getUserId());
		
		MentorSearchResult result = new MentorSearchResult(
				mentor,
				skill,
				mentorSkill,
				mentorCalendars,
				user.getFirstName(),
				user.getLastName(),
				user.getContactNumber(),
				groupTrainingsBySkillName(trainings));		
	
		return result;
	}
	
	/**Helper Methods**/
	private Map<String, List<Training>> groupTrainingsBySkillName(List<Training> trainings) {
		
		if(trainings.size() <= 0 || trainings == null) {
			return new HashMap<>(0);
		}
		
		Map<String, List<Training>> trainingMap = new HashMap<>();
		List<Training> trList = new ArrayList<>();
		for(Training training: trainings) {
			Skill s = skillService.getSkill(training.getSkillId());
			
			if(s == null) {
				continue;
			}
			
			if(trainingMap.get(s.getName()) == null) {
				trainingMap.put(s.getName(), trList = new ArrayList<>());
			}
			trList.add(training);
		}
		
		return trainingMap;
	}

}
