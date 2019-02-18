package com.ibm.mods.auth.service;

import com.ibm.mods.auth.model.SignupUser;
import com.ibm.mods.auth.model.User;

public interface UserService {

    User findOne(String username);
    User findById(Long id);
    User signup(SignupUser user) throws Exception;
    void sendRegistrationCodeEmail(String email, String code) throws Exception;
    boolean emailExists(String email);
	
}
