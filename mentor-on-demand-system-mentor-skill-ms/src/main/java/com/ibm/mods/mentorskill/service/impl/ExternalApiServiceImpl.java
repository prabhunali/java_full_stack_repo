package com.ibm.mods.mentorskill.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ibm.mods.mentorskill.model.Training;
import com.ibm.mods.mentorskill.service.ExternalApiService;
import com.ibm.mods.mentorskill.util.HttpApiCrud;
import com.ibm.mods.mentorskill.util.URIRootBuilder;
import com.ibm.mods.mentorskill.util.URL;

@Service(value = "externalApiService")
public class ExternalApiServiceImpl implements ExternalApiService {
	
	@Autowired
	private URIRootBuilder uriRootBuilder;
	
	@Autowired
	private HttpApiCrud httpApiCrud;
	
	@Override
	public List<Training> getMentorTainings(long mentorId, String status) {
		///training/mentor/{mentorId}/{status}
		String url = uriRootBuilder.trainingRootURL() + URL.TRAINING_BY_MENTOR + "/" + mentorId + "/" + status;
		Training trainings[] = httpApiCrud.get(url, Training[].class);
		return Arrays.asList(trainings);
	}
}
