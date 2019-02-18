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
import com.ibm.mods.mentorskill.model.MentorSkill;
import com.ibm.mods.service.MentorSkillService;
import com.ibm.mods.user.auth.model.ApiResponse;
import com.ibm.mods.util.URL;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MentorSkillController {

	@Autowired
	private MentorSkillService mentorSkillService;
	
	@GetMapping(URL.MENTOR_SKILL_BY_MENTOR_ID)
	public List<MentorSkill> findByMentorId(@RequestParam long mentorId) {
		return mentorSkillService.findByMentorId(mentorId);
	}
	
	@PostMapping(URL.MENTOR_SKILL_SAVE)
	public void saveMentorSkill(@RequestBody MentorSkill mentorSkill) {
		mentorSkillService.saveMentorSkill(mentorSkill);
	}
	
	@PutMapping(URL.MENTOR_SKILL_UPDATE)
	public ApiResponse<Void> updateMentorSkill(@RequestBody MentorSkill mentorSkill) {
		mentorSkillService.updateMentorSkill(mentorSkill);
		return new ApiResponse<>(HttpStatus.OK.value(), "Mentor Skill updated successfully.", null);
	}
	
	@DeleteMapping(URL.MENTOR_SKILL_DELETE)
	public ApiResponse<Void> deleteMentorSkill(@PathVariable long id) {
		mentorSkillService.deleteMentorSkill(id);
		return new ApiResponse<Void>(HttpStatus.OK.value(), "Mentor Skill successfully deleted.", null);
	}
	
}
