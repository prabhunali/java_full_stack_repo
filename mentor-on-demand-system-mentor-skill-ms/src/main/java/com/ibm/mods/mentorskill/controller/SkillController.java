package com.ibm.mods.mentorskill.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.ibm.mods.mentorskill.model.Skill;
import com.ibm.mods.mentorskill.service.AuthenticationService;
import com.ibm.mods.mentorskill.service.MentorSkillService;
import com.ibm.mods.mentorskill.service.SkillService;
import com.ibm.mods.mentorskill.util.URL;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SkillController {

	@Autowired
	private SkillService skillService;
	
	@Autowired
	private MentorSkillService mentorSkillService;
	
	// TODO method for testing only
	@GetMapping("/search_skill")
	public List<Skill> getSkillsByWildcardName(@RequestParam String skillName) {
		return skillService.getSkills(skillName);
	}
	
	@GetMapping(URL.SKILLS)
	public List<Skill> getSkills() {
		return skillService.getSkills();
	}
	
	@GetMapping(URL.SKILL_BY_ID)
	public Skill getSkill(@RequestParam long skillId) {
		return skillService.getSkill(skillId);
	}
	
	@GetMapping(URL.MENTOR_NOT_SKILLS)
	public List<Skill> getMentorNonSkills(@RequestParam long mentorId) {
		List<Skill> skills = skillService.getSkills();
		List<MentorSkill> mentorSkills = mentorSkillService.findByMentorId(mentorId);
		//List<Skill> mentorNonSkills = new ArrayList<>();
		
		for(Iterator<Skill> skill = skills.iterator(); skill.hasNext();) {
			Skill s = skill.next();
			for(MentorSkill mentorSkill : mentorSkills) {
				if(s.getId() == mentorSkill.getSkillId()) {
					skill.remove();
				}
			}
		}
		
		return skills;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(URL.SKILL_ADD)
	public Skill addSkill(@RequestBody Skill skill) {
		return skillService.addSkill(skill);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(URL.SKILL_EDIT)
	public Skill editSkill(@RequestBody Skill skill) {
		return skillService.editSkill(skill);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(URL.SKILL_DELETE)
	public void deleteSkill(@PathVariable long skillId) {
		skillService.deleteSkill(skillId);
	}

}
