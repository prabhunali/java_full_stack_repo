package com.ibm.mods.sched.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.mods.sched.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
	
	Payment findByTrainingId(long trainingId);

}
