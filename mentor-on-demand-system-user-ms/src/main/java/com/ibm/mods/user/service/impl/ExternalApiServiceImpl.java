package com.ibm.mods.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.mods.user.model.User;
import com.ibm.mods.user.model.UserProfile;
import com.ibm.mods.user.repository.UserRepository;
import com.ibm.mods.user.security.model.Mentor;
import com.ibm.mods.user.service.ExternalApiService;
import com.ibm.mods.user.util.HttpApiCrud;
import com.ibm.mods.user.util.URIRootBuilder;
import com.ibm.mods.user.util.URL;

@Service(value = "externalApiService")
public class ExternalApiServiceImpl implements ExternalApiService {
	
	@Autowired
	private URIRootBuilder uriRootBuilder;
	
	@Autowired
	private HttpApiCrud httpApiCrud;
	
	@Autowired
	private UserRepository userDao;
	
	@Override
	public boolean updateUserProfile(UserProfile userProfile) {
		// Update user
		User user = userDao.findById(userProfile.getId()).get();
		if(user == null) {
			return false;
		}
		
		user.setFirstName(userProfile.getFirstName());
		user.setLastName(userProfile.getLastName());
		user.setContactNumber(userProfile.getContactNumber());
		userDao.save(user);
		
		// Update Mentor
		/*
		 * String getMentorUrl = uriRootBuilder.mentorSkillRootURL() + URL.MENTOR +
		 * "?mentorId=" + userProfile.getId(); Mentor mentor = null; try { mentor =
		 * httpApiCrud.get(getMentorUrl, Mentor.class); } catch(Exception e) {
		 * e.printStackTrace(); } finally { // Allow mentor to complete registration
		 * if(mentor == null) { mentor = new Mentor(); mentor.setId(user.getId());
		 * mentor.setUsername(user.getUsername()); }
		 * 
		 * mentor.setLinkedinUrl(userProfile.getLinkedInUrl());
		 * mentor.setYearsOfExperience(userProfile.getTotalYearsExp());
		 * mentor.setIntroduction(userProfile.getIntroduction());
		 * 
		 * // Call Mentor Update API String saveMentorUrl =
		 * uriRootBuilder.mentorSkillRootURL() + URL.MENTOR_UPDATE;
		 * httpApiCrud.update(saveMentorUrl, mentor); }
		 */
    	
    	return true;
	}

	@Override
	public UserProfile getUserProfile(long mentorId) {
		User user = userDao.findById(mentorId).get();
		
		UserProfile userProfile = new UserProfile();
		userProfile.setId(user.getId());
		userProfile.setFirstName(user.getFirstName());
		userProfile.setLastName(user.getLastName());
		userProfile.setContactNumber(user.getContactNumber());
		
		// Call Mentor Controller API
		String url = uriRootBuilder.mentorSkillRootURL() + URL.MENTOR + "?mentorId=" + mentorId;
		Mentor mentor = null;
		
		try {
			mentor = httpApiCrud.get(url, Mentor.class);
		} catch(Exception e) {
			userProfile.setTotalYearsExp(0);
			userProfile.setLinkedInUrl("");
			userProfile.setIntroduction("");
			e.printStackTrace();
		}
		
		if(mentor == null) {
			userProfile.setTotalYearsExp(0);
			userProfile.setLinkedInUrl("");
			userProfile.setIntroduction("");
		} else {
			userProfile.setTotalYearsExp(mentor.getYearsOfExperience());
			userProfile.setLinkedInUrl(mentor.getLinkedinUrl());
			userProfile.setIntroduction(mentor.getIntroduction());
		}
		
		return userProfile;
	}

}
