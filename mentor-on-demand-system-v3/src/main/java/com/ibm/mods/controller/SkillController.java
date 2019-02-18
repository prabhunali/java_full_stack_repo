package com.ibm.mods.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.mods.mentorskill.model.MentorSkill;
import com.ibm.mods.service.MentorSkillService;
import com.ibm.mods.service.SkillService;
import com.ibm.mods.skill.model.Skill;
import com.ibm.mods.util.URL;

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
	
//	@GetMapping(URL.SKILL_BY_MENTOR_ID)
//	public List<Skill> getMentorSkills(@RequestParam long mentorId) {
//		List<Skill> skills = skillService.getSkills();
//		List<MentorSkill> mentorSkills = mentorSkillService.findByMentorId(mentorId);
//		//List<Skill> mentorNonSkills = new ArrayList<>();
//		
//		for(Iterator<Skill> skill = skills.iterator(); skill.hasNext();) {
//			Skill s = skill.next();
//			for(MentorSkill mentorSkill : mentorSkills) {
//				if(s.getId() != mentorSkill.getSkillId()) {
//					skill.remove();
//				}
//			}
//		}
//		
//		return skills;
//	}
}
