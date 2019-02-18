package com.ibm.mods.service;

import java.util.List;

import com.ibm.mods.skill.model.Skill;

public interface SkillService {

	List<Skill> getSkills(String name);
	List<Skill> getSkills();
	Skill getSkill(long skillId);
}
