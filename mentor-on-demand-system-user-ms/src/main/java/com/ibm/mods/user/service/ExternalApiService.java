package com.ibm.mods.user.service;

import com.ibm.mods.user.model.UserProfile;

public interface ExternalApiService {

	boolean updateUserProfile(UserProfile userProfile);
	public UserProfile getUserProfile(long mentorId);
	
}
