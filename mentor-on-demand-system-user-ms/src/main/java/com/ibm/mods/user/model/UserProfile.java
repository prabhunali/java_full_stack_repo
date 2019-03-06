package com.ibm.mods.user.model;

public class UserProfile {
	
	private long id;
	private String firstName;
	private String lastName;
	private int totalYearsExp;
	private String contactNumber;
	private String linkedInUrl;
	private String introduction;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getTotalYearsExp() {
		return totalYearsExp;
	}
	public void setTotalYearsExp(int totalYearsExp) {
		this.totalYearsExp = totalYearsExp;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getLinkedInUrl() {
		return linkedInUrl;
	}
	public void setLinkedInUrl(String linkedInUrl) {
		this.linkedInUrl = linkedInUrl;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

}
