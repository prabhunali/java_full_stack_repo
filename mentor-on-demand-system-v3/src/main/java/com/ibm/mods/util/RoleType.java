package com.ibm.mods.util;

public enum RoleType {

	USER("User Role"),
	MENTOR("Mentor Role"),
	ADMIN("Admin Role")
	;
	
	private final String description;
	
	RoleType(String description) {
		this.description = description;
	}
	
	public String getRoleDescription() {
		return this.description;
	}
}
