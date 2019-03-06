package com.ibm.mods.admin.service;

import java.util.List;

import com.ibm.mods.admin.model.AdminSetting;

public interface AdminSettingsService {
	
	List<AdminSetting> getAdminSettings();
	AdminSetting getAdminSettingById(long id);
	AdminSetting getAdminSettingByName(String name);
	AdminSetting addAdminSetting(AdminSetting setting);
	AdminSetting editAdminSetting(AdminSetting setting);
	void deleteAdminSetting(long id);

}
