package com.ibm.mods.sched.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.mods.sched.model.Training;

public interface TrainingRepository extends JpaRepository<Training, Long>{

	List<Training> findByStatus(String status);
	
}
