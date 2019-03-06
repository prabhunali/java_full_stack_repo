package com.ibm.mods.payment.service;

import java.util.List;

import com.ibm.mods.payment.model.Payment;

public interface PaymentService {

	List<Payment> getPayments();
	
}
