package com.ibm.mods.user.service;

import java.util.List;

import com.ibm.mods.user.model.SignupUser;
import com.ibm.mods.user.model.User;

public interface UserService {

    User findOne(String username);
    User findById(Long id);
    User signup(SignupUser user) throws Exception;
    User findByUsername(String username);
    void sendRegistrationCodeEmail(String email, String code) throws Exception;
    boolean emailExists(String email);
	String confirmUserEmail(String email, String token);
	// Administrator Role Services
	List<User> findAll();
	User blockUser(long userId, boolean block);
    
}
