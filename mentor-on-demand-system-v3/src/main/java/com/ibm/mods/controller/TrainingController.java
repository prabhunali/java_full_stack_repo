package com.ibm.mods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ibm.mods.service.TrainingService;
import com.ibm.mods.service.UserService;
import com.ibm.mods.training.model.Training;
import com.ibm.mods.util.TrainingStatus;
import com.ibm.mods.util.URL;

@Controller
public class TrainingController {

	@Autowired
	private TrainingService trainingService;
	
	@Autowired UserService userService;
	
	@PostMapping(URL.TRAINING_PROPOSE)
	public void proposeTraining(@RequestBody Training training) throws Exception {
		this.trainingService.proposeTraining(training);
	}
	
	@PutMapping(URL.TRAINING_CONFIRM)
	public void confirmTraining(@PathVariable long id, @PathVariable boolean confirmed) throws Exception {
		String status = "";
		if(confirmed) {
			status = TrainingStatus.CONFIRMED.get();
		} else {
			status = TrainingStatus.REJECTED.get();
		}
		trainingService.confirmTraining(id, status);
	}
	
	@PutMapping(URL.TRAINING_FINALIZE)
	public void finalizeTraining(@PathVariable long id, @PathVariable boolean finalized) throws Exception {
		String status = "";
		if(finalized) {
			status = TrainingStatus.FINALIZED.get();
		} else {
			status = TrainingStatus.CANCELLED.get();
		}
		trainingService.finalizeTraining(id, status);
	}
	
}
