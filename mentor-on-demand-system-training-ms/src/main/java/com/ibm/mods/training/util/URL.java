package com.ibm.mods.training.util;

public class URL {

	// Base URL
	//public static final String BASE_URL							= "www.mentorondemand.com";
	
	// Home URLs
	public static final String HOME 							= "/home";
	public static final String HOME_SEARCH_MENTOR 				= HOME + "/searchmentor";
	public static final String HOME_SEARCH_MENTOR_PROFILE 		= HOME + "/view_mentor_profile";
	public static final String HOME_VIEW_MENTOR 				= "/viewmentor";
	public static final String HOME_USER_SIGNIN 				= "signin";
	
	// User URLs
	public static final String USERS 							= "/users";
//	public static final String USER_SIGNUP						= USER + "/signup";
	public static final String USER_SEARCH_MENTOR 				= USERS + "/search";
	public static final String USER_PROPOSE_TO_MENTOR 			= "/propose";
	public static final String USER_FINALIZE_PROPOSAL 			= USERS + "/finalizeProposal";
	public static final String USER_VIEW_TRAININGS 				= USERS + "/viewtrainings";
	public static final String USER_GET_BY_ID					= USERS + "/{id}";
	public static final String USER_UPDATE_PROFILE				= USERS + "/update";
	public static final String USER_GET_PROFILE					= USERS + "/profile";
	
	// Mentor URLs
	public static final String MENTOR							= "/mentor"; 
//	public static final String MENTOR_SIGNUP 					= MENTOR + "/signup";
	public static final String MENTOR_VIEW_HISTORY	 			= MENTOR + "/viewHistory";
	public static final String MENTOR_SEND_EMAIL_NOTIF 			= MENTOR + "/sendMailNotif";
	public static final String MENTOR_SAVE						= MENTOR + "/save";
	public static final String MENTOR_UPDATE					= MENTOR + "/edit";
	public static final String MENTOR_DELETE					= MENTOR + "/delete";
	
	// Authentication
	public static final String AUTH_SAVE						= "/save";
	public static final String AUTH_GET_LIST					= "/all";
	public static final String AUTH_GET_UPDATE_DELETE_ONE		= "/{id}";
//	public static final String AUTH_UPDATE						= "/{id}";
//	public static final String AUTH_DELETE						= "/{id}";
	public static final String AUTH_SIGNUP						= "/signup";
	public static final String AUTH_SIGNUP_CHECK_EMAIL			= AUTH_SIGNUP + "/test";
	
	// Training
	public static final String TRAINING_PROPOSE					= "/training/propose";
	public static final String TRAINING_CONFIRM					= "/training/confirm/{id}/{confirmed}";
	public static final String TRAINING_FINALIZE				= "/training/finalize/{id}/{finalized}";
	public static final String TRAINING_BY_USER					= "/training/user";
	public static final String TRAINING_BY_MENTOR				= "/training/mentor";
	public static final String TRAINING_BY_MENTOR_AND_STATUS	= "/training/mentor/{mentorId}/{status}";
	
	// Skills
	public static final String SKILLS							= "/skills";
	public static final String SKILL_BY_ID						= "/skill";
	public static final String SKILL_BY_MENTOR_ID				= "/skills/by_mentor_id";
	public static final String SKILL_ADD						= "/skill/add";
	public static final String SKILL_EDIT						= "/skill/edit";
	public static final String SKILL_DELETE						= "/skill/delete/{skillId}";
	
	// Mentor Skill
	public static final String MENTOR_SKILL_BY_MENTOR_ID		= "/mentor_skills";
	public static final String MENTOR_SKILL_SAVE				= "/mentor_skills/save";
	public static final String MENTOR_SKILL_UPDATE				= "/mentor_skills/edit";
	public static final String MENTOR_SKILL_DELETE				= "/mentor_skills/delete/{id}";
	public static final String MENTOR_NOT_SKILLS				= "/mentor_skills/not_mentor_skills";
	
	// Mentor Calendar
	public static final String MENTOR_CAL						= "/mentor_cals";
	public static final String MENTOR_CAL_BY_MENTOR_ID			= "/mentor_cals";
	public static final String MENTOR_CAL_SAVE					= MENTOR_CAL + "/save";
	public static final String MENTOR_CAL_UPDATE				= MENTOR_CAL + "/edit";
	public static final String MENTOR_CAL_DELETE				= MENTOR_CAL + "/delete/{id}";
	
}
