package com.ibm.mods.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ibm.mods.skill.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>{

	List<Skill> findByName(String name);
	
	//SELECT * FROM Skill s WHERE s.name LIKE '%Java%';
	@Query("SELECT s FROM Skill s WHERE s.name LIKE %:name%")
	List<Skill> findByWildcardName(String name);
	
}
