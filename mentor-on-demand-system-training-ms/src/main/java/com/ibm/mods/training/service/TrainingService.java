package com.ibm.mods.training.service;

import java.util.List;

import com.ibm.mods.training.model.Training;
import com.ibm.mods.training.model.TrainingDto;
import com.ibm.mods.training.security.config.ApiResponse;

public interface TrainingService {

	Training findById(long id);
	ApiResponse<Void> proposeTraining(Training training) throws Exception;
	ApiResponse<Void> confirmTraining(long id, String status) throws Exception;
	ApiResponse<Void> finalizeTraining(long id, String status) throws Exception;
	void updateTrainingStatus(long id, String status);
	
	//Select Training By XXX
	Training getTraining(long id);
	List<Training> getTrainingsByUser(long userId);
	List<Training> getTrainingsByUser(long userId, String status);
	List<Training> getTrainingsByMentor(long mentorId);
	List<Training> getTrainingsByMentor(long mentorId, String status);
	List<Training> getTrainingsByStatus(String status);
	
}
