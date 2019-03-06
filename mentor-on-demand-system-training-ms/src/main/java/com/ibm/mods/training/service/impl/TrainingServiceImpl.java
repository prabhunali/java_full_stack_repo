package com.ibm.mods.training.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ibm.mods.training.model.Skill;
import com.ibm.mods.training.model.Training;
import com.ibm.mods.training.model.TrainingProgressCalculator;
import com.ibm.mods.training.repository.TrainingRepository;
import com.ibm.mods.training.repository.UserRepository;
import com.ibm.mods.training.security.config.ApiResponse;
import com.ibm.mods.training.security.model.User;
import com.ibm.mods.training.service.TrainingService;
import com.ibm.mods.training.util.EmailSender;
import com.ibm.mods.training.util.HttpApiCrud;
import com.ibm.mods.training.util.TrainingStatus;
import com.ibm.mods.training.util.URIRootBuilder;
import com.ibm.mods.training.util.URL;

import static com.ibm.mods.training.util.MessageUtil.*;
import static com.ibm.mods.training.util.TrainingUtil.*;

@Service(value = "trainingService")
public class TrainingServiceImpl implements TrainingService {
	
	@Autowired
	private TrainingRepository trainingRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private EmailSender emailSender;
	
	@Autowired
	private URIRootBuilder uriRootBuilder;
	
	@Autowired
	private HttpApiCrud httpApiCrud;
	
	@Override
	public ApiResponse<Void> proposeTraining(Training training) throws Exception {
		
		TrainingProgressCalculator trainingCalc = new TrainingProgressCalculator
				(
					training.getStartDate(),
					training.getEndDate(),
					dateToString(SQL_TIME_FORMAT, training.getStartTime()),
					dateToString(SQL_TIME_FORMAT, training.getEndTime()),
					training.getDaysOfSession(),
					training.getProgress()
				 );
		
		double trainingTotalAmount = trainingCalc.getTrainingTotalHours() * training.getTrainingTotalAmount();
		
		Training newTraining = new Training();
		newTraining.setUserId(training.getUserId());
		newTraining.setMentorId(training.getMentorId());
		newTraining.setMentorSkillId(training.getMentorSkillId());
		newTraining.setSkillId(training.getSkillId());
		newTraining.setStatus(training.getStatus());
		newTraining.setProgress(training.getProgress());
		newTraining.setRating(training.getRating());
		newTraining.setStartDate(training.getStartDate());
		newTraining.setEndDate(training.getEndDate());
		newTraining.setStartTime(training.getStartTime());
		newTraining.setEndTime(training.getEndTime());
		newTraining.setDaysOfSession(training.getDaysOfSession());
		newTraining.setTrainingTotalHours(trainingCalc.getTrainingTotalHours());
		newTraining.setTrainingTotalAmount(trainingTotalAmount); // totalHours * mentorHourlyRate
		
		// Save Training Details
		if(trainingRepo.save(newTraining) != null) {
			// Notify the Mentor via Email that a training has been proposed by a user
			
			// Get User and Mentor Details
			User mentor = userRepo.getOne(newTraining.getUserId());
			User user = userRepo.getOne(newTraining.getUserId());
			
			// Get Training Skill Details
			String url = uriRootBuilder.mentorSkillRootURL() + URL.SKILL_BY_ID + "?skillId=" + newTraining.getSkillId();
			Skill skill = httpApiCrud.get(url, Skill.class);
			
			// Notify mentor via email
			emailSender.sendMimeEmail(mentor.getUsername()
					, PROPOSE_TRAINING_EMAIL_SUBJ
					, getProposeTrainingMessage(mentor.getFirstName()
											  , user.getFirstName() + " " + user.getLastName()
											  , skill.getName()));
			return new ApiResponse<Void>(HttpStatus.OK.value(), "Training proposal was successfully processed.", null);
		}
		
		return new ApiResponse<Void>(HttpStatus.OK.value(), "Training proposal was not successfully processed.", null);
	}
	
	
	@Override
	public ApiResponse<Void> confirmTraining(long id, String status) throws Exception {
		
		// Mentor Confirms Training Proposal
		Training training = trainingRepo.getOne(id);
		
		if(training != null) {
			training.setStatus(status);
			trainingRepo.save(training);
			
			// Notify user via e-mail that proposed training has been confirmed by Mentor
			
			// Get User and Mentor Details
			User user = userRepo.getOne(training.getUserId());
			User mentor = userRepo.getOne(training.getUserId());
			
			// Get Training Skill Details
			String url = uriRootBuilder.mentorSkillRootURL() + URL.SKILL_BY_ID + "?skillId=" + training.getSkillId();
			Skill skill = httpApiCrud.get(url, Skill.class);
			
			emailSender.sendMimeEmail(user.getUsername()
					, CONFIRM_TRAINING_EMAIL_SUBJ
					, getConfirmTrainingMessage(mentor.getFirstName() + " " + mentor.getLastName()
											  , user.getFirstName() + " " + user.getLastName()
											  , skill.getName()
											  , status==TrainingStatus.CONFIRMED.get() ? true : false));
		
			return new ApiResponse<Void>(HttpStatus.OK.value(), "Training proposal was successfully processed.", null);
		
		}
		
		return new ApiResponse<Void>(HttpStatus.NOT_MODIFIED.value(), "Training proposal was not successfully processed.", null);
	}

	@Override
	public ApiResponse<Void> finalizeTraining(long id, String status) throws Exception {
		// Mentor Confirms Training Proposal
		Training training = trainingRepo.getOne(id);
		
		if(training != null) {
			training.setStatus(status);
			trainingRepo.save(training);
			
			User mentor = userRepo.getOne(training.getUserId());
			User student = userRepo.getOne(training.getUserId());
			
			// Get Training Skill Details
			String url = uriRootBuilder.mentorSkillRootURL() + URL.SKILL_BY_ID + "?skillId=" + training.getSkillId();
			Skill skill = httpApiCrud.get(url, Skill.class);
					
			// Notify user that proposed training has been confirmed by Mentor
			User user = userRepo.getOne(training.getMentorId());
			emailSender.sendMimeEmail(user.getUsername()
					, FINALIZE_TRAINING_EMAIL_SUBJ
					, getFinalizeTrainingMessage(mentor.getFirstName()
										       , student.getFirstName() + " " + student.getLastName()
										       , skill.getName()
										       , status==TrainingStatus.FINALIZED.get() ? true : false));
			
			return new ApiResponse<Void>(HttpStatus.OK.value(), "Training proposal was successfully finalized.", null);
		}
		
		return new ApiResponse<Void>(HttpStatus.NOT_MODIFIED.value(), "Training proposal was not successfully finalized.", null);
	}

	@Override
	public void updateTrainingStatus(long id, String status) {
		Training training = trainingRepo.getOne(id);
		training.setStatus(status);
		trainingRepo.save(training);
	}

	@Override
	public Training findById(long id) {
		return trainingRepo.getOne(id);
	}

	@Override
	public Training getTraining(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Training> getTrainingsByUser(long userId) {
		return trainingRepo.findByUserId(userId);
	}

	@Override
	public List<Training> getTrainingsByUser(long userId, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Training> getTrainingsByMentor(long mentorId) {
		return trainingRepo.findByMentorId(mentorId);
	}

	@Override
	public List<Training> getTrainingsByMentor(long mentorId, String status) {
		List<Training> trainings =  trainingRepo.getTrainings(mentorId, status);
		return (trainings.size() <= 0 || trainings == null) ?  new ArrayList<Training>() : trainings;
	}

	@Override
	public List<Training> getTrainingsByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
