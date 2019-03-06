package com.ibm.mods.admin.service;

import java.util.List;

import com.ibm.mods.admin.model.Skill;

public interface AdminService {
	
	// Skills
	List<Skill> getSkills(String name);
	List<Skill> getSkills();
	Skill getSkill(long skillId);
	Skill addSkill(Skill skill);
	Skill editSkill(Skill skill);
	void deleteSkill(long id);
	

}
