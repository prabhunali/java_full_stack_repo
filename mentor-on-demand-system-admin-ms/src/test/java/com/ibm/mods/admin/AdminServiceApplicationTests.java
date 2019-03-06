package com.ibm.mods.admin;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ibm.mods.admin.model.AdminSetting;
import com.ibm.mods.admin.repository.AdminSettingsRepository;
import com.ibm.mods.admin.service.impl.AdminSettingsServiceImpl;
import com.ibm.mods.model.DummyData;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceApplicationTests {

	@Test
	public void contextLoads() {
	}
	
//	@Autowired
//	 private DummyData data;
	 
	 @Mock
	 private AdminSettingsRepository adminSettingsMock;
	 
	 @InjectMocks
	 AdminSettingsServiceImpl adminService;
	 
//	 @Test
//	 public void getSkillsTest() throws Exception {
//		
//		  when(adminServiceMock.getSkills()).thenReturn(data.getSkills());
//		  assertEquals(24, businessImpl.findTheGreatestFromAllData());		 
//		 
//	 }
	 
	 @Test
	 public void testGetAdminSettings() {
		 AdminSetting settings = new AdminSetting();
		 settings.setId(3);
		 settings.setName("admin_payment_comissio");
		 settings.setDescription("20");
		 
		 when(adminSettingsMock.findByName("admin_payment_comission")).thenReturn(settings);
			assertEquals(settings.getName(), adminService.getAdminSettingByName("admin_payment_comission").getName());
	 }

}
