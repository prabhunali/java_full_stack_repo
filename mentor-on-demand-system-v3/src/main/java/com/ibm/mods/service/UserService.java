package com.ibm.mods.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.ibm.mods.user.model.User;
import com.ibm.mods.user.model.UserProfile;

public interface UserService {

    List<User> findAll();
    void delete(long id);
    User findOne(String username);
    User blockUser(String username);
    User findById(Long id);
    void updateUserProfile(UserProfile userProfile);
    UserProfile getUserProfile(long mentorId);
    //User confirmEmail(SignupUser userdto);
	
}
