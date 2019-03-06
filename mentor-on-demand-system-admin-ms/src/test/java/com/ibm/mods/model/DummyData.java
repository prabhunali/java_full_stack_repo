package com.ibm.mods.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.mods.admin.model.Skill;

@Component
public class DummyData {

	public List<Skill> getSkills() {
		List<Skill> skills = new ArrayList<>();
		 
		 Skill skill1 = new Skill();
		 skill1.setId(9);
		 skill1.setName("Java");
		 skill1.setDescription("Java Desc");
		 skill1.setPrerequisites("n/a");
		 
		 Skill skill2 = new Skill();
		 skill2.setId(10);
		 skill2.setName("VB.net");
		 skill2.setDescription("VB.net Desc");
		 skill2.setPrerequisites("n/a");
		 
		 skills.add(skill1);
		 skills.add(skill2);
		 return skills;
	}
	
	
	
}
