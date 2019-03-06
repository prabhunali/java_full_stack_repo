package com.ibm.mods.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.mods.admin.model.AdminSetting;
import com.ibm.mods.admin.service.AdminSettingsService;
import static com.ibm.mods.admin.util.URL.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AdminSettingsController {
	
	@Autowired
	private AdminSettingsService adminSettingsService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(ADMIN_SETTINGS_GET_ALL)
	public List<AdminSetting> getAdminSettings() {
		return this.adminSettingsService.getAdminSettings();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(ADMIN_SETTINGS_GET_BY_ID)
	public AdminSetting getAdminSettingById(@PathVariable long id) {
		return this.adminSettingsService.getAdminSettingById(id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(ADMIN_SETTINGS_GET_BY_NAME)
	public AdminSetting getAdminSettingByName(@PathVariable String name) {
		return this.adminSettingsService.getAdminSettingByName(name);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(ADMIN_SETTINGS_ADD)
	public AdminSetting addAdminSetting(@RequestBody AdminSetting setting) {
		 return this.adminSettingsService.addAdminSetting(setting);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(ADMIN_SETTINGS_EDIT)
	public AdminSetting editAdminSetting(@RequestBody AdminSetting setting) {
		return this.adminSettingsService.editAdminSetting(setting);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(ADMIN_SETTINGS_DELETE)
	public void deleteAdminSetting(@RequestParam long id) {
		this.adminSettingsService.deleteAdminSetting(id);
	}

}
