package com.ibm.mods.mentorskill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.mods.mentorskill.model.Mentor;

public interface MentorRepository extends JpaRepository<Mentor, Long> {

	Mentor findByUsername(String username);
	Mentor findByUserId(long id);
	
}
