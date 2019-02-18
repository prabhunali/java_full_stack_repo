package com.ibm.mods.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.mods.mentorskill.model.MentorSkill;
import com.ibm.mods.repository.MentorSkillRepository;
import com.ibm.mods.service.MentorSkillService;

@Service(value = "mentorSkillService")
public class MentorSkillServiceImpl implements MentorSkillService {

	@Autowired
	private MentorSkillRepository mentorSkillRepo;
	
	@Override
	public Map<Long, List<MentorSkill>> getMentorSkills(List<Long> ids) {
		
		List<MentorSkill> mentorSkills = mentorSkillRepo.findAllById(ids);
		
		Map<Long, List<MentorSkill>> map = new TreeMap<>();
		List<MentorSkill> groupedMentorSkills = null;
	
		if(mentorSkills.size() != 0) {
			for(MentorSkill ms : mentorSkills) {
				if(map.get(ms.getId()) == null) {
					map.put(ms.getId(), groupedMentorSkills = new ArrayList<>());
				}
				groupedMentorSkills.add(ms);
			}
		}
		
		return map;
	}

	@Override
	public void saveMentorSkill(MentorSkill ms) {	
		if(!existsBySkillId(ms.getSkillId())) {
			mentorSkillRepo.save(ms);
		}
		//return findByMentorId(ms.getMentorId());
	}

	@Override
	public List<MentorSkill> findByMentorId(long mentorId) {
		List<MentorSkill> mentorSkills = mentorSkillRepo.findByMentorId(mentorId);
		if(mentorSkills.size() <= 0 || mentorSkills == null) {
			return new ArrayList<>();
		}
		return mentorSkills;
	}
	
	private boolean existsBySkillId(long skillId) {
		List<MentorSkill> mentorSkills = mentorSkillRepo.findBySkillId(skillId);
		if(mentorSkills.size() == 0 || mentorSkills.equals(null)) {
			return false;
		}
		
		return true;
	}

	@Override
	public MentorSkill findMentorSkill(long mentorSkillId, long mentorId, long skillId) {
		return mentorSkillRepo.findMentorSkill(mentorSkillId, mentorId, skillId);
	}

	@Override
	public MentorSkill findMentorSkillById(long mentorSkillId) {
		return mentorSkillRepo.findById(mentorSkillId).get();
	}

	@Override
	public List<MentorSkill> findAllBySkillId(List<Long> skillIds) {
		return mentorSkillRepo.findAllBySkillId(skillIds);
	}

	@Override
	public void updateMentorSkill(MentorSkill mentorSkill) {
		mentorSkillRepo.save(mentorSkill);
	}

	@Override
	public void deleteMentorSkill(long id) {
		mentorSkillRepo.deleteById(id);
	}

}
