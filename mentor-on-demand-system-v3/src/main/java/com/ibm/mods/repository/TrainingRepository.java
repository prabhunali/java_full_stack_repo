package com.ibm.mods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.mods.training.model.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long>{

}
