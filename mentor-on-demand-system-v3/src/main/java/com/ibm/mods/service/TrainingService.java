package com.ibm.mods.service;

import com.ibm.mods.training.model.Training;

public interface TrainingService {

	Training findById(long id);
	void proposeTraining(Training training) throws Exception;
	void confirmTraining(long id, String status) throws Exception;
	void finalizeTraining(long id, String status) throws Exception;
	void updateTrainingStatus(long id, String status);
	
}
