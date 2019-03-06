package com.ibm.mods.payment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="payment")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="training_id")
	private long trainingId;
	
	@Column(name="txn_type")
	private String txnType;
	
	@Column(name="payment_by_user")
	private double paymentByUser;
	
	@Column(name = "payment_to_admin")
	private double paymentToAdmin;
	
	@Column(name="running_payment_to_mentor")
	private double paymentToMentor;
	
	@Column(name = "running_pyment_to_mentor_percentage")
	private double paymentToMentorPercentage;
	
	@Column(name="remarks")
	private String remarks;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(long trainingId) {
		this.trainingId = trainingId;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public double getPaymentByUser() {
		return paymentByUser;
	}

	public void setPaymentByUser(double paidByUser) {
		this.paymentByUser = paidByUser;
	}

	public double getPaymentToAdmin() {
		return paymentToAdmin;
	}

	public void setPaymentToAdmin(double paymentToAdmin) {
		this.paymentToAdmin = paymentToAdmin;
	}

	public double getPaymentToMentor() {
		return paymentToMentor;
	}

	public void setPaymentToMentor(double paidToMentor) {
		this.paymentToMentor = paidToMentor;
	}

	public double getPaymentToMentorPercentage() {
		return paymentToMentorPercentage;
	}

	public void setPaymentToMentorPercentage(double paidToMentorPercentage) {
		this.paymentToMentorPercentage = paidToMentorPercentage;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
