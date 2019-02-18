package com.ibm.mods.util;

public enum TrainingStatus {

	PROPOSED	("Proposed"),	// User/Trainee will send training proposal to a selected mentor
	CONFIRMED	("Confirmed"),	// Mentor will confirm/reject user's training proposal
	REJECTED	("Rejected"),	// Mentor rejected user's proposal
	FINALIZED	("Finalized"),	// User will finalize confirmed proposal by the mentor
	ONGOING		("On-going"),	// Progress is > 0% (Training has already started)
	COMPLETED	("Completed"),	// Training is completed
	CANCELLED	("Cancelled"),	// Training was cancelled by either user or mentor
	;
	
	private final String status;
	
	private TrainingStatus(String status) {
		this.status = status;
	}
	
	public String get() {
		return this.status;
	}
}
