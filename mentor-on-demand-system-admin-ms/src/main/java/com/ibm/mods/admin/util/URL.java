package com.ibm.mods.admin.util;

public class URL {
	
	public static final String MODS_SERVICE_PATH = "/mods-api";
	public static final String TEST_SERVICE_PATH = "/test-api";
	
	public static final String ZUUL_ROOT_PATH	 = "";
	
	// User URLs
	public static final String HOME 				= "/home";
	public static final String AUTH_SIGNUP			= "/signup";
	public static final String AUTH_SIGNIN			= "/token/generate-token";
	public static final String USER_VERIFY_EMAIL 	= "/verify_email/{email}/{token}";
	
	// Users
	public static final String USERS 				= "/users";
	public static final String USER_BLOCK			= USERS + "/blockuser";
	public static final String USER_BY_USERNAME		= USERS + "/username";
	
	// Skills
	public static final String SKILLS							= "/skills";
	public static final String SKILL_BY_ID						= "/skill";
	public static final String SKILL_BY_MENTOR_ID				= "/skills/by_mentor_id";
	public static final String SKILL_ADD						= "/skill/add";
	public static final String SKILL_EDIT						= "/skill/edit";
	public static final String SKILL_DELETE						= "/skill/delete";
	
	//  Admin URLs
	public static final String ADMIN							= "/admin";
	public static final String ADMIN_SIGNUP						= ADMIN + "/signup";
	public static final String ADMIN_ADD_TECHNOLOGY 			= ADMIN + "/technology/add";
	public static final String ADMIN_REMOVE_TECHNOLOGY 			= ADMIN + "technology/remove";;
	public static final String ADMIN_SEARCH_AND_VIEW_PAYMENTS 	= ADMIN + "/searchAndViewPayments";
	public static final String ADMIN_DISPLAY_REPORTS 			= ADMIN + "/displayReports";
	public static final String ADMIN_EDIT_PARAMETERS		 	= ADMIN + "/editParameters";
	public static final String ADMIN_BLOCK_USER					= ADMIN + "/blockuser/{userId}/{block}";
	public static final String ADMIN_GET_USERS					= ADMIN + "/users";
	public static final String ADMIN_GET_SKILLS					= ADMIN + "/skills";
	public static final String ADMIN_SKILL_DELETE				= ADMIN + SKILL_DELETE + "/{skillId}";
	
	// Admin Settings
	public static final String ADMIN_SETTINGS					= ADMIN + "/settings";
	public static final String ADMIN_SETTINGS_GET_ALL			= ADMIN_SETTINGS + "/all";
	public static final String ADMIN_SETTINGS_GET_BY_ID			= ADMIN_SETTINGS + "/id/{id}";
	public static final String ADMIN_SETTINGS_GET_BY_NAME		= ADMIN_SETTINGS + "/name/{name}";
	public static final String ADMIN_SETTINGS_ADD				= ADMIN_SETTINGS + "/add";
	public static final String ADMIN_SETTINGS_EDIT				= ADMIN_SETTINGS + "/edit";
	public static final String ADMIN_SETTINGS_DELETE			= ADMIN_SETTINGS + "/delete";
	

}
