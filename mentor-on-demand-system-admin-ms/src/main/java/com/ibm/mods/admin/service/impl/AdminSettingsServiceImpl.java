package com.ibm.mods.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.mods.admin.model.AdminSetting;
import com.ibm.mods.admin.repository.AdminSettingsRepository;
import com.ibm.mods.admin.service.AdminSettingsService;

@Service(value = "adminSettingsService")
public class AdminSettingsServiceImpl implements AdminSettingsService {

	@Autowired
	private AdminSettingsRepository adminDao;
	
	@Override
	public List<AdminSetting> getAdminSettings() {
		return adminDao.findAll();
	}

	@Override
	public AdminSetting getAdminSettingById(long id) {
		return adminDao.findById(id).get();
	}

	@Override
	public AdminSetting getAdminSettingByName(String name) {
		return adminDao.findByName(name);
	}

	@Override
	public AdminSetting addAdminSetting(AdminSetting setting) {
		return adminDao.save(setting);
	}

	@Override
	public AdminSetting editAdminSetting(AdminSetting setting) {
		return adminDao.save(setting);
	}

	@Override
	public void deleteAdminSetting(long id) {
		adminDao.deleteById(id);
	}

}
