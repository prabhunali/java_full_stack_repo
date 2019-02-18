package com.ibm.mods.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ibm.mods.mentorskill.model.MentorSkill;

@Repository
public interface MentorSkillRepository extends JpaRepository<MentorSkill, Long>{

	List<MentorSkill> findByMentorId(long mentorId);
	List<MentorSkill> findBySkillId(long skillId);
	
	@Query("SELECT ms FROM MentorSkill ms WHERE ms.id = :mentorSkillId AND ms.mentorId = :mentorId AND ms.skillId = :skillId")
	MentorSkill findMentorSkill(@Param("mentorSkillId") long mentorSkillId
						      , @Param("mentorId") long mentorId
						      , @Param("skillId") long skillId);
	
	@Query("SELECT ms FROM MentorSkill ms WHERE ms.skillId IN :skillIds")
	List<MentorSkill> findAllBySkillId(List<Long> skillIds);
	
}
