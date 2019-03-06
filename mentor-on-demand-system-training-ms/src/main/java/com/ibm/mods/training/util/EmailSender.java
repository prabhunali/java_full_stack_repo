package com.ibm.mods.training.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

	@Autowired
	private JavaMailSender sender;
	
	private String recipient;
	private String subject;
	private String message;
	
	public EmailSender() {
		
	}
	
	public EmailSender(String recipient, String subject, String message) {
		this.recipient = recipient;
		this.subject = subject;
		this.message = message;
	}
	
	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void sendMimeEmail() throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	    helper.setTo(this.recipient);
	    helper.setSubject(this.subject);
	    helper.setText(this.message, true);
	    sender.send(message);
	}
	
	public void sendSimpleEmail() {
		SimpleMailMessage emailMessage = new SimpleMailMessage();
		emailMessage.setTo(this.recipient);
		emailMessage.setSubject(this.subject);
		emailMessage.setText(this.message);
		sender.send(emailMessage);
	}
	
	// TODO USE THIS ONE IT'S WORKING
	public void sendSimpleEmail(String recipientEmail, String subject, String message) {
		SimpleMailMessage emailMessage = new SimpleMailMessage();
		emailMessage.setTo(recipientEmail);
		emailMessage.setSubject(subject);
		emailMessage.setText(message);
		sender.send(emailMessage);
	}
	
	// TODO USE THIS ONE IT'S WORKING
	public void sendMimeEmail(String emailRecipient, String subject, String text) throws Exception {
	    MimeMessage message = sender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	    helper.setTo(emailRecipient);
	    helper.setSubject(subject);
	    helper.setText(text, true);
	    sender.send(message);
	}
	
	public static void main(String args[]) {
		
	}
	
}
