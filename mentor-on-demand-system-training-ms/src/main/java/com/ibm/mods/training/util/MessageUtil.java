package com.ibm.mods.training.util;

public class MessageUtil {
	
	public static final String PROPOSE_TRAINING_EMAIL_SUBJ = "Mentor-on-Demand: Training Has Been Proposed";
	public static final String CONFIRM_TRAINING_EMAIL_SUBJ = "Mentor-on-Demand: Training Has Been Confirmed/Rejected";
	public static final String FINALIZE_TRAINING_EMAIL_SUBJ = "Mentor-on-Demand: Training Has Been Finalized/Cancelled";
	
	public static final String getProposeTrainingMessage(String mentorName, String studentName, String skillName) {
		StringBuilder msg = new StringBuilder();
		msg.append("<p><b>Hi ").append(mentorName).append("</b>,</p>")
		   .append("<p>A potential new student has contacted you from your profile on Mentor-on-Demand!</p>")
		   .append("<hr/>")
		   .append("<p><b>Name: </b>").append(studentName).append("</p>")
		   .append("<p><b>Skill: </b>").append(skillName).append("</p>")
		   .append("<hr/>")
		   .append("<p>Please login to your account on Mentor-on-Demand to view Student's information as well as to confirm/reject student's training proposal.</p>")
		   .append("<br/><p>Thank you,</p>")
		   .append("<p>Mentor-on-Demand Administrator</p>");
		return msg.toString();
	}
	
	public static final String getConfirmTrainingMessage(String mentorName, String studentName, String skillName, boolean confirmed) {
		StringBuilder msg = new StringBuilder();
		msg.append("<p><b>Hi ").append(studentName).append("</b>,</p>")
		   .append("<p>The mentor (").append(mentorName).append(") has ").append(confirmed ? "confirmed" : "rejected")
		   .append(" your training proposal.</p>")
		   .append("<hr/>")
		   .append("<p><b>Name: </b>").append(studentName).append("</p>")
		   .append("<p><b>Skill: </b>").append(skillName).append("</p>")
		   .append("<hr/>")
		   .append("<p>Please login to your account on Mentor-on-Demand to view the remarks and other details.</p>");
		   
		   if(!confirmed) { 
			   msg.append("<p>You may also consider to check for another mentor and re-submit another training proposal.</p>");
		   }
		  
		   msg.append("<br/><p>Thank you,</p>")
		   .append("<p>Mentor-on-Demand Administrator</p>");
		return msg.toString();
	}
	
	public static final String getFinalizeTrainingMessage(String mentorName, String studentName, String skillName, boolean finalized) {
		StringBuilder msg = new StringBuilder();
		msg.append("<p><b>Hi ").append(mentorName).append("</b>,</p>")
		   .append("<p>The user (").append(studentName).append(") has ").append(finalized ? "finalized" : "cancelled")
		   .append(" the training.</p>")
		   .append("<hr/>")
		   .append("<p><b>Name: </b>").append(studentName).append("</p>")
		   .append("<p><b>Skill: </b>").append(skillName).append("</p>")
		   .append("<hr/>");
		   if(finalized) {
			   msg.append("<p>Please contact the user to start the training sessions based on agreement.</p>");
		   }
		   msg.append("<br/><p>Thank you,</p>")
		   .append("<p>Mentor-on-Demand Administrator</p>");
		return msg.toString();
	}
	
}
