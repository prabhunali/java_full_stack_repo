package com.ibm.mods.payment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.mods.payment.model.Payment;
import com.ibm.mods.payment.repository.PaymentRepository;
import com.ibm.mods.payment.service.PaymentService;

@Service(value = "paymentService")
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentDao;
	
	@Override
	public List<Payment> getPayments() {
		return paymentDao.findAll();
	}
	
}
