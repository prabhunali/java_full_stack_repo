package com.ibm.mods.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.mods.training.model.Training;
import com.ibm.mods.training.model.TrainingDto;
import com.ibm.mods.training.security.config.ApiResponse;
import com.ibm.mods.training.service.TrainingService;
import com.ibm.mods.training.util.TrainingStatus;
import com.ibm.mods.training.util.URL;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TrainingController {

	@Autowired
	private TrainingService trainingService;
	
	@PostMapping(URL.TRAINING_PROPOSE)
	public ApiResponse<Void> proposeTraining(@RequestBody Training training) throws Exception {
		return this.trainingService.proposeTraining(training);
	}
	
	@PutMapping(URL.TRAINING_CONFIRM)
	public ApiResponse<Void> confirmTraining(@PathVariable long id, @PathVariable boolean confirmed) throws Exception {
		String status = "";
		if(confirmed) {
			status = TrainingStatus.CONFIRMED.get();
		} else {
			status = TrainingStatus.REJECTED.get();
		}
		return trainingService.confirmTraining(id, status);
	}
	
	@PutMapping(URL.TRAINING_FINALIZE)
	public ApiResponse<Void> finalizeTraining(@PathVariable long id, @PathVariable boolean finalized) throws Exception {
		String status = "";
		if(finalized) {
			status = TrainingStatus.FINALIZED.get();
		} else {
			status = TrainingStatus.CANCELLED.get();
		}
		return trainingService.finalizeTraining(id, status);
	}
	
	@GetMapping(URL.TRAINING_BY_USER)
	public List<Training> getTrainingsByUser(@RequestParam long id) {
		return trainingService.getTrainingsByUser(id);
	}
	
	@GetMapping(URL.TRAINING_BY_MENTOR)
	public List<Training> getTrainingsByMentor(@RequestParam long id) {
		return trainingService.getTrainingsByMentor(id);
	}
	
//	@GetMapping(URL.TRAINING_BY_MENTOR)
//	public List<Training> getCompletedTrainingsByMentor(@PathVariable long mentorId) {
//		return trainingService.getTrainingsByMentor(mentorId, TrainingStatus.COMPLETED.get());
//	}
	
	@GetMapping(URL.TRAINING_BY_MENTOR_AND_STATUS)
	public List<Training> getMentorTrainingsByStatus(@PathVariable long mentorId, @PathVariable String status) {
		return trainingService.getTrainingsByMentor(mentorId, status);
	}
	
}
