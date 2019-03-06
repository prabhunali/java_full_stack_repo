package com.ibm.mods.mentorskill.service;

import java.util.List;

import com.ibm.mods.mentorskill.model.Skill;

public interface SkillService {

	List<Skill> getSkills(String name);
	List<Skill> getSkills();
	Skill getSkill(long skillId);
	Skill addSkill(Skill skill);
	Skill editSkill(Skill skill);
	void deleteSkill(long id);
}
