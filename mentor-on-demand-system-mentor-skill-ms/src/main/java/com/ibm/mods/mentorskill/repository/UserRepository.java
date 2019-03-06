package com.ibm.mods.mentorskill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.mods.mentorskill.security.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

}
