package com.ibm.mods.service.impl;

import static com.ibm.mods.util.MessageUtil.getProposeTrainingMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.mods.repository.SkillRepository;
import com.ibm.mods.repository.TrainingRepository;
import com.ibm.mods.repository.UserRepository;
import com.ibm.mods.service.TrainingService;
import com.ibm.mods.skill.model.Skill;
import com.ibm.mods.training.model.Training;
import com.ibm.mods.user.model.User;
import com.ibm.mods.util.EmailSender;
import com.ibm.mods.util.TrainingStatus;

import static com.ibm.mods.util.MessageUtil.*;

@Service(value = "trainingService")
public class TrainingServiceImpl implements TrainingService {

	@Autowired
	private TrainingRepository trainingRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private SkillRepository skillRepo;
	
	@Autowired
	private EmailSender emailSender;
	
	@Override
	public void proposeTraining(Training training) throws Exception {
		// Save Training Details
		trainingRepo.save(training);
		
		// Notify the Mentor via Email that a training has been proposed by a user
		User mentor = userRepo.getOne(training.getMentorId());
		User user = userRepo.getOne(training.getUserId());
		Skill skill = skillRepo.getOne(training.getSkillId());
		
		emailSender.sendMimeEmail(mentor.getUsername()
				, PROPOSE_TRAINING_EMAIL_SUBJ
				, getProposeTrainingMessage(mentor.getFirstName()
										  , user.getFirstName() + " " + user.getLastName()
										  , skill.getName()));
	}
	
	@Override
	public void confirmTraining(long id, String status) throws Exception {
		// Mentor Confirms Training Proposal
		Training training = trainingRepo.getOne(id);
		training.setStatus(status);
		trainingRepo.save(training);
		
		// Notify user via e-mail that proposed training has been confirmed by Mentor
		User user = userRepo.getOne(training.getUserId());
		User mentor = userRepo.getOne(training.getMentorId());
		Skill skill = skillRepo.getOne(training.getSkillId());
		
		emailSender.sendMimeEmail(user.getUsername()
				, CONFIRM_TRAINING_EMAIL_SUBJ
				, getConfirmTrainingMessage(mentor.getFirstName() + " " + mentor.getLastName()
										  , user.getFirstName() + " " + user.getLastName()
										  , skill.getName()
										  , status==TrainingStatus.CONFIRMED.get() ? true : false));
	}

	@Override
	public void finalizeTraining(long id, String status) throws Exception {
		// Mentor Confirms Training Proposal
		Training training = trainingRepo.getOne(id);
		training.setStatus(status);
		trainingRepo.save(training);
		
		User mentor = userRepo.getOne(training.getMentorId());
		User student = userRepo.getOne(training.getUserId());
		Skill skill = skillRepo.getOne(training.getSkillId());
				
		// Notify user that proposed training has been confirmed by Mentor
		User user = userRepo.getOne(training.getMentorId());
		emailSender.sendMimeEmail(user.getUsername()
				, FINALIZE_TRAINING_EMAIL_SUBJ
				, getFinalizeTrainingMessage(mentor.getFirstName()
									       , student.getFirstName() + " " + student.getLastName()
									       , skill.getName()
									       , status==TrainingStatus.FINALIZED.get() ? true : false));
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

}
