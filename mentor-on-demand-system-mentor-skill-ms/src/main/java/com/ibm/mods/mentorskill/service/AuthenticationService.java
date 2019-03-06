package com.ibm.mods.mentorskill.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ibm.mods.mentorskill.security.model.User;

public interface AuthenticationService extends UserDetailsService {
	
	User getUserById(long id);

}
