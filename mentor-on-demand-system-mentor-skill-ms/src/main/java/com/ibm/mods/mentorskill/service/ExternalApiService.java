package com.ibm.mods.mentorskill.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ibm.mods.mentorskill.model.Training;

public interface ExternalApiService {

	List<Training> getMentorTainings(long mentorId, String status);
	
}
