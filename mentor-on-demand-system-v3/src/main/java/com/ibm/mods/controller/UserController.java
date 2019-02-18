package com.ibm.mods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.ibm.mods.mentor.model.Mentor;
import com.ibm.mods.mentor.model.MentorDto;
import com.ibm.mods.service.UserService;
import com.ibm.mods.user.model.User;
import com.ibm.mods.user.model.UserProfile;
import com.ibm.mods.user.auth.model.ApiResponse;
import com.ibm.mods.user.model.SignupUser;
import com.ibm.mods.util.EmailSender;
import com.ibm.mods.util.URL;
import com.ibm.mods.util.Utils;

import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private EmailSender emailSender;

    //@PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value=URL.USERS, method = RequestMethod.GET)
    public List<User> listUser(){
        return userService.findAll();
    }

    //@PreAuthorize("hasAnyRole('USER', 'MENTOR')")
    ////@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = URL.USER_GET_BY_ID, method = RequestMethod.GET)
    public User getUser(@PathVariable(value = "id") Long id){
        return userService.findById(id);
    }
    
    // TODO for testing only
    @GetMapping(URL.AUTH_SIGNUP_CHECK_EMAIL)
    public void emailExists() throws Exception {
    	//emailSender = new EmailSender("jekjektorres_agustin@yahoo.com", "test", "Test");
    	emailSender.sendMimeEmail("jekjektorres_agustin@yahoo.com", "test", "<b>Test</b> <br> <hr/> <p>Another </p>");
    }
    
    @PutMapping(URL.USER_UPDATE_PROFILE)
    public ApiResponse<Void> updateUSerProfile(@RequestBody UserProfile userProfile) {
    	
    	// Call Mentor Service API to save mentor updates
//    	final String uri = "http://localhost:8083/mentor/edit";
//    	MentorDto mentorDto = new MentorDto();
//    	mentorDto.setId(userProfile.getId());
//    	mentorDto.setLinkedinUrl(userProfile.getLinkedInUrl());
//    	mentorDto.setYearsOfExperience(userProfile.getTotalYearsExp());
//    	mentorDto.setIntroduction(userProfile.getIntroduction());
//    	RestTemplate restTemplate = new RestTemplate();
//    	restTemplate.put(uri, mentorDto, MentorDto.class);
    	
    	// Save User updates
    	userService.updateUserProfile(userProfile);
    	return new ApiResponse<Void>(HttpStatus.OK.value(), "User profile successfully updated", null);
    }
    
    @GetMapping(URL.USER_GET_PROFILE)
    public UserProfile getUserProfile(@RequestParam long mentorId) {
    	return this.userService.getUserProfile(mentorId);
    }
    
    public void blockUser() {}
    
    public void confirmUser(@RequestParam String regCode) {
    	
    }
    
    @GetMapping("/test/hostname")
    public String test(HttpServletRequest req) throws MalformedURLException {
    	StringBuilder sb = new StringBuilder();
    	sb.append("Request URL: " + req.getRequestURL().toString() + "\n");
    	sb.append("Request URI: " + req.getRequestURI().toString() + "\n");
    	sb.append("Server Name: " + req.getServerName() + "\n");
    	sb.append("Servlet Path: " + req.getServletPath() + "\n");
    	sb.append("Servlet Context: " + req.getServletContext() + "\n");
    	sb.append("Context Path: " + req.getContextPath() + "\n");
    	sb.append("Local Address: " + req.getLocalAddr() + "\n");
    	sb.append("Local Name: " + req.getLocalName() + "\n");
    	sb.append("Remote Address: " + req.getRemoteAddr() + "\n");
    	sb.append("Remote Host: " + req.getRemoteHost() + "\n");
    	sb.append("Remote Port: " + req.getRemotePort() + "\n");
    	sb.append("Remote User: " + req.getRemoteUser() + "\n");
    	sb.append("Root URI: " + Utils.getRootURI(req));
        return sb.toString();
    }

}
