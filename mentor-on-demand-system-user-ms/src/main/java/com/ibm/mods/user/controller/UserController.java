package com.ibm.mods.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.ibm.mods.user.model.User;
import com.ibm.mods.user.model.UserProfile;
import com.ibm.mods.user.security.config.ApiResponse;
import com.ibm.mods.user.service.ExternalApiService;
import com.ibm.mods.user.service.UserService;
import com.ibm.mods.user.util.URL;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private ExternalApiService externalApiService;
    
    //@PreAuthorize("hasAnyRole('USER', 'MENTOR')")
    @RequestMapping(value = URL.USER_GET_BY_ID, method = RequestMethod.GET)
    public User getUser(@PathVariable(value = "id") Long id){
        return userService.findById(id);
    }
    
    @PreAuthorize("hasAnyRole('USER', 'MENTOR', 'ADMIN')")
    @GetMapping(URL.USER_GET_BY_USERNAME)
    public User getUser(@PathVariable String username) {
    	return userService.findByUsername(username);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(URL.USERS)
    public List<User> getUsers() {
    	return userService.findAll();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(URL.USER_BLOCK)
    public ApiResponse<Void> blockUser(@PathVariable long userId, @PathVariable boolean block) {
    	if(userService.blockUser(userId, block) != null) {
    		return new ApiResponse<Void>(HttpStatus.OK.value(), "User has been blocked/unblocked.", null);
    	}
    	return new ApiResponse<Void>(HttpStatus.OK.value(), "Failed to block/unblock user.", null);
    }
    
    @PutMapping(URL.USER_UPDATE_PROFILE)
    public ApiResponse<Void> updateUSerProfile(@RequestBody UserProfile userProfile) {
    	// Save User updates
    	boolean updated = externalApiService.updateUserProfile(userProfile);
    	String message = updated ? "User profile has been successfully updated" : "Failed to update user profile";
    	int responseCode = updated ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value();
    	return new ApiResponse<Void>(responseCode, message, null);
    }
    
    @GetMapping(URL.USER_GET_PROFILE)
    public UserProfile getUserProfile(@RequestParam long mentorId) {
    	return this.externalApiService.getUserProfile(mentorId);
    }
    
    

}
