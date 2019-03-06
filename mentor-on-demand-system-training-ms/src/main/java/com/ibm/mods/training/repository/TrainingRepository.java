package com.ibm.mods.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ibm.mods.training.model.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long>{
	
	//SELECT * FROM TRAININGS WHERE skill_id IN(4, 21) AND mentor_id IN (12, 21) AND status = 'Completed';
	@Query("SELECT t FROM Training t WHERE t.mentorId = :mentorId AND t.status = :status")
	public List<Training> getTrainings(@Param(value = "mentorId") long mentorId, @Param(value = "status") String status);

	public List<Training> findByUserId(long userId);
	public List<Training> findByMentorId(long mentorId);
	
}
