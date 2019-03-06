package com.ibm.mods.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ibm.mods.payment.security.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.username = :username AND u.registrationCode = :token")
	User findByUsernameAndToken(String username, String token);
	
	User findByUsername(String username);

}
