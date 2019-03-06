package com.ibm.mods.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.mods.admin.model.AdminSetting;

@Repository
public interface AdminSettingsRepository extends JpaRepository<AdminSetting, Long>{

	AdminSetting findByName(String name);
	
}
