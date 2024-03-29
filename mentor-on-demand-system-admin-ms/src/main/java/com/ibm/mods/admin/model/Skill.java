package com.ibm.mods.admin.model;

public class Skill {
	
	private long id;
	private String name;
	private String description;
	private String prerequisites;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPrerequisites() {
		return prerequisites;
	}
	
	public void setPrerequisites(String prerequisites) {
		this.prerequisites = prerequisites;
	}

}
