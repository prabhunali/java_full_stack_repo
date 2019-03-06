package com.ibm.mods.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.mods.payment.model.Payment;
import com.ibm.mods.payment.service.PaymentService;
import com.ibm.mods.payment.util.URL;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping(URL.PAYMENT_GET_ALL)
	public List<Payment> getPayments() {
		return paymentService.getPayments();
	}

}
