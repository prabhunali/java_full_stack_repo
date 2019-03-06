package com.ibm.mods.training.service;

import javax.servlet.http.HttpServletRequest;

import com.ibm.mods.training.model.Skill;
import com.ibm.mods.training.security.model.User;

public interface ExternalApiService {
	
	User getUser(HttpServletRequest request, long id);
	Skill getSkill(HttpServletRequest request, long id);

}
