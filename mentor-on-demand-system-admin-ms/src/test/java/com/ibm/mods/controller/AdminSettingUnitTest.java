package com.ibm.mods.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ibm.mods.admin.model.AdminSetting;
import com.ibm.mods.admin.repository.AdminSettingsRepository;
import com.ibm.mods.admin.service.AdminSettingsService;
import com.ibm.mods.admin.service.impl.AdminSettingsServiceImpl;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class AdminSettingUnitTest {
	
	@TestConfiguration
    static class AdminSettingServiceImplTestContextConfiguration {
  
        @Bean
        public AdminSettingsService adminService() {
            return new AdminSettingsServiceImpl();
        }
    }
	
	@Autowired
	private AdminSettingsService adminSettingService;
	
	@MockBean
	private AdminSettingsRepository adminRepository;
	
	@Before
	public void setUp() {
		// Multiple Records (list) Dummy Objects
		List<AdminSetting> adminSettingsFromRepo = new ArrayList<>();
		AdminSetting s1 = new AdminSetting();
		s1.setId(1);
		s1.setName("setting1");
		s1.setDescription("setting 1 desc");
		
		AdminSetting s2 = new AdminSetting();
		s2.setId(2);
		s2.setName("setting2");
		s2.setDescription("setting 2 desc");
		
		AdminSetting s3 = new AdminSetting();
		s3.setId(3);
		s3.setName("setting3");
		s3.setDescription("setting 3 desc");
		
		adminSettingsFromRepo = Arrays.asList(s1, s2, s3);
		// getAdminSettings
		Mockito.when(adminRepository.findAll()).thenReturn(adminSettingsFromRepo);
		
		// Single Record Dummy Object
		
		// getAdminSettingByName
		AdminSetting setting = new AdminSetting();
		setting.setId(1);
		setting.setName("setting");
		setting.setDescription("setting desc");
		Mockito.when(adminRepository.findByName(setting.getName())).thenReturn(setting);
	    
	}
	
	@Test
	public void getAdminSettingByNameTest() {
	    String name = "setting";
	    AdminSetting setting = adminSettingService.getAdminSettingByName(name);
	     assertThat(setting.getName()).isEqualTo(name);
	 }
	
	@Test
	public void getAdminSettingsTest() {
		List<AdminSetting> expected = new ArrayList<>();
		AdminSetting s1 = new AdminSetting();
		s1.setId(1);
		s1.setName("setting1");
		s1.setDescription("setting 1 desc");
		
		AdminSetting s2 = new AdminSetting();
		s2.setId(2);
		s2.setName("setting2");
		s2.setDescription("setting 2 desc");
		
		AdminSetting s3 = new AdminSetting();
		s3.setId(3);
		s3.setName("setting3");
		s3.setDescription("setting 3 desc");
		expected = Arrays.asList(s1, s2, s3);
		
		List<AdminSetting> actual = adminSettingService.getAdminSettings();
		
		for(int x=0; x<expected.size(); x++) { 
		  assertThat(actual.get(x).getId(), is(expected.get(x).getId())); 
		  assertThat(actual.get(x).getName(), is(expected.get(x).getName()));
		  assertThat(actual.get(x).getDescription(), is(expected.get(x).getDescription())); 
		}
	}
	
	@Test
	public void getAdminSettingByIdTest() {
		 // getAdminSettingById
		AdminSetting expected = new AdminSetting();
		expected.setId(1);
		expected.setName("setting");
		expected.setDescription("setting desc");
		Optional<AdminSetting> optional = Optional.of(expected);		
	    Mockito.when(adminRepository.findById(expected.getId())).thenReturn(optional);
		
		AdminSetting actual = adminSettingService.getAdminSettingById(1);
	    assertThat(actual.getId()).isEqualTo(optional.get().getId());
	    assertThat(actual.getName()).isEqualTo(optional.get().getName());
	    assertThat(actual.getDescription()).isEqualTo(optional.get().getDescription());
	}
	
	@Test
	public void addAdminSettingTest() {
		AdminSetting settingAdd = new AdminSetting();
	    settingAdd.setId(1);
	    settingAdd.setName("setting");
	    settingAdd.setDescription("setting desc");
        when(adminRepository.save(settingAdd)).thenReturn(settingAdd);
        assertThat(adminSettingService.addAdminSetting(settingAdd), is(notNullValue()));
	}
	
	@Test
	public void editAdminSetting() {
		AdminSetting expected = new AdminSetting();
		expected.setId(1);
		expected.setName("setting updated");
		expected.setDescription("setting desc updated");
		when(adminRepository.save(expected)).thenReturn(expected);
		AdminSetting actual = adminSettingService.editAdminSetting(expected);
		assertThat(actual, is(notNullValue()));
	}
	

}
