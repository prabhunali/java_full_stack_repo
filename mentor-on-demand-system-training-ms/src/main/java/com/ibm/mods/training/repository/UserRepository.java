package com.ibm.mods.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.mods.training.security.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

}
