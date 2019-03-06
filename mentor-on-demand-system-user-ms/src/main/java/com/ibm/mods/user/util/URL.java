package com.ibm.mods.user.util;

public class URL {
	
	public static final String MODS_SERVICE_PATH = "/mods-api";
	public static final String TEST_SERVICE_PATH = "/test-api";
	
	public static final String ZUUL_ROOT_PATH	 = "";
	
	// User URLs
	public static final String HOME 				= "/home";
	public static final String USERS 				= "/users";
	public static final String USER_GET_BY_USERNAME	= USERS + "/username/{username}";
	public static final String AUTH_SIGNUP			= "/signup";
	public static final String AUTH_SIGNIN			= "/token/generate-token";
	public static final String USER_VERIFY_EMAIL 	= "/verify_email/{email}/{token}";
	public static final String USER_VERIFY_EMAIL_2 	= "/verify_email";
	public static final String USER_GET_BY_ID		= USERS + "/{id}";
	public static final String USER_UPDATE_PROFILE	= USERS + "/update";
	public static final String USER_GET_PROFILE		= USERS + "/profile";
	public static final String USER_BLOCK			= USERS + "/blockuser/{userId}/{block}";
	
	// Mentor URLs
	public static final String MENTOR							= "/mentor"; 
	public static final String MENTOR_VIEW_HISTORY	 			= MENTOR + "/viewHistory";
	public static final String MENTOR_SEND_EMAIL_NOTIF 			= MENTOR + "/sendMailNotif";
	public static final String MENTOR_SAVE						= MENTOR + "/save";
	public static final String MENTOR_UPDATE					= MENTOR + "/edit";
	public static final String MENTOR_DELETE					= MENTOR + "/delete";
	
	

}
