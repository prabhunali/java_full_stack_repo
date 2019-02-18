package com.ibm.mods.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.mods.mentorskill.model.MentorSkill;
import com.ibm.mods.repository.SkillRepository;
import com.ibm.mods.service.SkillService;
import com.ibm.mods.skill.model.Skill;

@Service(value = "skillService")
public class SkillServiceImpl implements SkillService {

	@Autowired
	private SkillRepository skillRepository;
	
	@Override
	public List<Skill> getSkills(String name) {
		List<Skill> skills = skillRepository.findByWildcardName(name);
		return skills;
	}

	@Override
	public List<Skill> getSkills() {
		List<Skill> skills = new ArrayList<Skill>();
		skills = skillRepository.findAll();
		return skills;
	}

	@Override
	public Skill getSkill(long skillId) {
		return skillRepository.getOne(skillId);
	}

}
