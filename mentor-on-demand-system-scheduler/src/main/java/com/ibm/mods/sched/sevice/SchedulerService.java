package com.ibm.mods.sched.sevice;

import static com.ibm.mods.sched.config.Utility.SQL_TIME_FORMAT;
import static com.ibm.mods.sched.config.Utility.dateToString;

import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ibm.mods.sched.model.Payment;
import com.ibm.mods.sched.model.Training;
import com.ibm.mods.sched.model.TrainingProgressCalculator;
import com.ibm.mods.sched.repo.PaymentRepository;
import com.ibm.mods.sched.repo.TrainingRepository;

@Service
public class SchedulerService {
	
	@Autowired
	private TrainingRepository trainingRepo;
	
	@Autowired
	private PaymentRepository paymentRepo;
	
	public List<Training> getTrainingsOnGoing(String status) {
		List<Training> trainings = trainingRepo.findByStatus(status);
		return trainings == null ? new ArrayList<Training>() : trainings;
	}
	
	public Payment gettrainingPayment(long trainingId) {
		return paymentRepo.findByTrainingId(trainingId);
	}
	
	public boolean updateTrainingProgress(Training training) {
		if(trainingRepo.save(training) != null) {
			return true;
		}
		return false;
	}
	
	public boolean updatePayment(Payment payment) {
		if(paymentRepo.save(payment) != null) {
			return true;
		}
		return false;
	}
}
