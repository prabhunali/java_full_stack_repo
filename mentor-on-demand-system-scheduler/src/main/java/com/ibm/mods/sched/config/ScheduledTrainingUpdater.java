package com.ibm.mods.sched.config;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.ibm.mods.sched.config.Utility.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ibm.mods.sched.model.Payment;
import com.ibm.mods.sched.model.Training;
import com.ibm.mods.sched.model.TrainingProgressCalculator;
import com.ibm.mods.sched.sevice.SchedulerService;

@Component
public class ScheduledTrainingUpdater {

	@Autowired
	private SchedulerService schedulerService;
	
	private static final Logger log = LoggerFactory.getLogger(ScheduledTrainingUpdater.class);

    //@Scheduled(fixedRate = 10000)
	@Scheduled(fixedRateString = "${schedule.fixedRate}")
    public void updateTrainingProgress() throws Exception {
		
		log.info("#########################################################################################");
    	log.info("Scheduled Microservice for updating Training and Payment will run now!");
    	log.info(". . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .");
		
    	// Get list of On-going training
    	List<Training> trainings = schedulerService.getTrainingsOnGoing("On-going");
    	
    	if(trainings.size() <= 0 || trainings == null) {
    		log.warn("No on-going training/s to process. Aborting......");
    		return;
    	}
    	
    	for(Training training : trainings) {
    		Payment payment = schedulerService.gettrainingPayment(training.getId());
    		
    		if(payment == null) {
    			log.warn("Can't determine payment record for training with id [" + training.getId() + "]");
    			log.warn("Skipping . . . . .");
    			continue;
    		}
    		
    		if(payment.getPaymentByUser() <= 0.00) {
    			log.warn("Training [" + payment.getTrainingId() + "] is not yet paid by user/student. ");
    			log.warn("Skipping . . . . .");
    			continue;
    		}
    		
    		 TrainingProgressCalculator trainingProgressCalculator = new TrainingProgressCalculator(
    				 training.getStartDate(),
    				 training.getEndDate(),
    				 dateToString(SQL_TIME_FORMAT, training.getStartTime()),
    				 dateToString(SQL_TIME_FORMAT, training.getEndTime()),
    				 training.getDaysOfSession(),
    				 training.getProgress());
    		 
    		 log.info("Displaying info of TRAINING with [ID] = " + training.getId() + " as of PREVIOUS run...........");
    		 log.info("Training id: " + training.getId());
    		 log.info("Training Start Date: " + trainingProgressCalculator.getStartDate());
    		 log.info("Training End Date: " + trainingProgressCalculator.getEndDate());
    		 log.info("Training Start Time: " + trainingProgressCalculator.getStartTime());
    		 log.info("Training End Time: " + trainingProgressCalculator.getEndTime());
    		 log.info("Training Session Days: " + trainingProgressCalculator.getDaysOfSession());
    		 log.info("Total # of Sessions: " + trainingProgressCalculator.getTotalNumberOfSessions());
    		 log.info("Hours / Session: " + trainingProgressCalculator.getHoursPerSession());
    		 log.info("Total # Training Hours: " + trainingProgressCalculator.getTrainingTotalHours());
    		 log.info("Progress / Session: " + trainingProgressCalculator.getProgressPerSession());
    		 log.info("YESTERDAY's Progress" + trainingProgressCalculator.getPreviousProgress());
    		 log.info("TODAY'S Progress: " + trainingProgressCalculator.getCurrentProgress());
    		 log.info("");
    		 log.info("");
    		 log.info("Displaying info of PAYMENT [" + payment.getId() + "] for Training [" + training.getId() +"] as of PREVIOUS run...........");
    		 log.info("Payment Id: " + payment.getId());
    		 log.info("Training Id: " + payment.getTrainingId());
    		 log.info("TXN Type: " + payment.getTxnType());
    		 log.info("Total Amount Paid By User: " + payment.getPaymentByUser());
    		 log.info("Remarks: " + payment.getRemarks());
    		 log.info("Current Payment to Mentor: " + payment.getPaymentToMentor());		 
    		 log.info("Current Payment Percentage: " + payment.getPaymentToMentorPercentage());
    		 
    		 double progressYesterday = trainingProgressCalculator.getPreviousProgress();
    		 double progressToday = trainingProgressCalculator.getCurrentProgress();    		 
    		 
    		 // Update training progress as of today
    		 if(doTrainingUpdate(training.getDaysOfSession(), training.getStartDate(), training.getEndDate(), training.getEndTime())) {
    			 log.info("Found training to update!");
    			 
    			 if(progressToday >= 100.00) {
    				 training.setStatus("Completed");
    			 }
    			 training.setProgress(progressToday);
    			 
    			 if(schedulerService.updateTrainingProgress(training)) {
    				log.info("Successfully updated below Training columnns with new data: ");
    				log.info("progress: " + training.getProgress());
    				log.info("status: " + training.getStatus());
    			 }
    		 }
    		 
    		 if(doPaymentUpdate(progressYesterday, progressToday)) {
    			 log.info("A payment record is legible for Payment Update!");
    			 double percentage = getUpdatePercentage(
    					 trainingProgressCalculator.getProgressPerSession(),
    					 progressYesterday,
    					 payment.getPaymentToMentorPercentage()
    					 );
    			
    			 log.info("Percentage to use for Mentor Payment: " + percentage);
    			 
    			 // double paymentToMentor =  payment.getPaidToMentor() + ((payment.getPaidByUser()-getPaidToAdmin()) * (percentage / 100));
    			double paymentToMentor =  payment.getPaymentToMentor() + (payment.getPaymentByUser() * (percentage / 100));
    			double currentPercentage = payment.getPaymentToMentorPercentage() + percentage;
    			payment.setPaymentToMentor(paymentToMentor);
    			payment.setPaymentToMentorPercentage(currentPercentage);
    			
    			if(schedulerService.updatePayment(payment)) {
    				log.info("Successfully updated below Payment columnns with new data: ");
    			} else {
    				log.error("Payment [id = " + payment.getId() + "] was NOT successfully updated with below details:");
    			}
    			
				log.info("Current Payment to Mentor: " + paymentToMentor);
				log.info("Current percentage of payment to mentor: " + currentPercentage);
    		 } else {
    			 log.info("There is nothing to update for payment with id = " + payment.getId());
    		 }
    		 
    	}
    		
    }
    
    private boolean doPaymentUpdate(double progressYesterday, double progressToday) {
    	if(progressYesterday < 25.00 && progressToday >= 25.00) {
    		return true;
    	} else if(progressYesterday < 50.00 && progressToday >= 50.00) {
    		return true;
    	} else if(progressYesterday < 75.00 && progressToday >= 75.00) {
    		return true;
    	} else if(progressYesterday < 100.00 && progressToday >= 100.00) {
    		return true;
    	}
    	return false;
    }
    
    private static boolean doTrainingUpdate(String daysOfSession, Date startDate, Date endDate, Date endTime ) throws ParseException {
    	List<String> sessionDays = Arrays.asList(daysOfSession.split(","));
    	
    	// Today
    	Calendar calendar = Calendar.getInstance();
    	
    	// Calendar is set to UTC initially to resolve Mysql UTC issue, add 1 day to get today
    	calendar.add(Calendar.DATE, 1);
    	Date today = calendar.getTime();
    	
    	String day = dateToString(DAY_FORMAT, today);
    	
    	log.warn("Update Training Parameterrs---------------------------");
    	log.warn("Today Date: " + today + "| Day today: " + day + " | Start Date: " + startDate + " | End Date: " + endDate + " | End Time: " + endTime);
    	
    	// Criteria to do Training Progress Update
    	// Today is between Training Start and End Date
    	// Training Days of Session contains today's day (.e.g. Mon)
    	// Today's time is after Training End Time
    	if(!sessionDays.contains(day)) {
    		log.warn("Training is not scheduled today.");
    		return false;
    	}
    	
    	if(today.after(startDate) && today.before(endDate) && todayTimeIsAfter(endTime)) {
    		log.warn("Training is legible for UPDATE!");
    		return true;
    	}
    	
    	return false;
    }
    
    private static double getUpdatePercentage(double progressPerSession, double progressYesterday, double currentPaymentPercentage) {
    	// Check initial Percentage to use.
    	if(currentPaymentPercentage == 0.00) {
    		if(progressPerSession == 100.00) {
    			return 100.00;
    		} else if(progressPerSession == 50.00) {
    			return 50.00;     		
    		} else {
    			return 25.00;
    		}
    	} else if(currentPaymentPercentage == 25.00) {
    		return 25.00;
    	} else if(currentPaymentPercentage == 50.00) {
    		if(progressPerSession == 50.00) {
    			return 50.00;
    		} else {
    			return 25.00;
    		}
    	} else if(currentPaymentPercentage == 75.00) {
    		return 25.00;
    	}
    	
    	return 0.00;
    }
    
    public static void main(String[] args) throws ParseException {
    	// Total Hours = 50 hours; Hours/Session = 5 hours; 
//    	double currentPaymentPercentage = 50.00;
//    	double progressYesterday = 0.00;
//    	double progressPerSession = 66.00;
//    	
//    	System.out.println(getUpdatePercentage(progressPerSession, progressYesterday, currentPaymentPercentage));
    	
    	String daysOfSession = "Mon,Tue";
    	Date startDate = stringToDate(SQL_DATE_FORMAT, "2019-02-25");
    	Date endDate = stringToDate(SQL_DATE_FORMAT, "2019-02-26");
    	Date endTime = stringToDate(SQL_TIME_FORMAT, "03:00:00");
    	
    	System.out.println("startDate: " + startDate);
    	System.out.println("endDate: " + endDate);
    	System.out.println("endTime: " + endTime);
    	System.out.println(doTrainingUpdate(daysOfSession, startDate, endDate, endTime));
    }
	
}
