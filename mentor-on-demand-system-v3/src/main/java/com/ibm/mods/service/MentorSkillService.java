package com.ibm.mods.service;

import java.util.List;
import java.util.Map;

import com.ibm.mods.mentorskill.model.MentorSkill;

public interface MentorSkillService {

	// Read
	void saveMentorSkill(MentorSkill ms);
	Map<Long, List<MentorSkill>> getMentorSkills(List<Long> ids);
	List<MentorSkill> findByMentorId(long mentorId);
	MentorSkill findMentorSkill(long mentorSkillId, long mentorId, long skillId);
	MentorSkill findMentorSkillById(long mentorSkillId);
	List<MentorSkill> findAllBySkillId(List<Long> skillIds);
	
	// Update
	void updateMentorSkill(MentorSkill mentorSkill);
	
	// Delete
	void deleteMentorSkill(long id);
	
}
