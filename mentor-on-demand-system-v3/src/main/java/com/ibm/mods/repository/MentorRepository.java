package com.ibm.mods.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.mods.mentor.model.Mentor;

public interface MentorRepository extends JpaRepository<Mentor, Long> {

	Mentor findByUsername(String username);
	
}
