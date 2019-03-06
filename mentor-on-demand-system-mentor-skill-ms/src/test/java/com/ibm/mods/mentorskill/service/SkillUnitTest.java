package com.ibm.mods.mentorskill.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ibm.mods.mentorskill.model.Skill;
import com.ibm.mods.mentorskill.repository.SkillRepository;
import com.ibm.mods.mentorskill.service.impl.SkillServiceImpl;

@RunWith(SpringRunner.class)
public class SkillUnitTest {
	
	@TestConfiguration
    static class SkillServiceImplTestContextConfiguration {
  
        @Bean
        public SkillService adminService() {
            return new SkillServiceImpl();
        }
    }
	
	@Autowired
	private SkillService skillService;
	
	@MockBean
	private SkillRepository skillDao;
	
	@Test
	public void getSkillsByWildCardNameTest() {
		List<Skill> skills = new ArrayList<>();
		String skillWildCardName = "Java";
		Mockito.when(skillDao.findByWildcardName(skillWildCardName)).thenReturn(skills);
		
	    List<Skill> actual = skillService.getSkills(skillWildCardName);
	    assertThat(actual, is(notNullValue()));
	}
	
	@Test
	public void getSkillsTestWithResult() {
		List<Skill> expected = new ArrayList<>();
		Skill s = new Skill();
		s.setId(1L);
		s.setName("skill name");
		s.setDescription("skill desc");
		s.setPrerequisites("skill prereq");
		expected.add(s);
		Mockito.when(skillDao.findAll()).thenReturn(expected);
		
		List<Skill> actualSkills = skillService.getSkills();
		for(Skill actualSkill : actualSkills) {
			assertThat(actualSkill.getId(), is(1L));
			assertThat(actualSkill.getName(), is("skill name"));
			assertThat(actualSkill.getDescription(), is("skill desc"));
			assertThat(actualSkill.getPrerequisites(), is("skill prereq"));
		}
	}
	
	public void getSkillsTestWithNoResult() {
		List<Skill> expected = new ArrayList<>();
		Mockito.when(skillDao.findAll()).thenReturn(expected);
		List<Skill> actualSkills = skillService.getSkills();
		assertNull(actualSkills);
	}
	
	@Test
	public void getSkillByIdTestReturnWithResult() {
		Skill expected = new Skill();
		expected.setId(1);
		expected.setName("skill name");
		expected.setDescription("skill desc");
		expected.setPrerequisites("skill prereq");
		Mockito.when(skillDao.getOne(expected.getId())).thenReturn(expected);
		Skill actual = skillService.getSkill(1);
		assertSame(actual, expected);
	}
	
	@Test
	public void getSkillByIdTestReturnNoResult() {
		Skill expected = new Skill();
		expected.setId(2);
		expected.setName("skill name");
		expected.setDescription("skill desc");
		expected.setPrerequisites("skill prereq");
		Mockito.when(skillDao.getOne(expected.getId())).thenReturn(expected);
		Skill actual = skillService.getSkill(1);
		assertNull(actual);
	}
	
	@Test
	public void addSkillTest() {
		Skill expected = new Skill();
		expected.setId(2);
		expected.setName("skill name");
		expected.setDescription("skill desc");
		expected.setPrerequisites("skill prereq");
		Mockito.when(skillDao.save(expected)).thenReturn(expected);
		Skill actual = skillService.addSkill(expected);
		assertNotNull(actual);
		assertSame(expected, actual);
	}
	
	@Test
	public void deleteSkillTest() {}
	
	@Test
	public void editSkillTest() {
//		Skill expected = new Skill();
//		expected.setId(2);
//		expected.setName("skill name");
//		expected.setDescription("skill desc");
//		expected.setPrerequisites("skill prereq");
//		doNothing().when(skillService.editSkill(expected));
		
//		doNothing().doThrow(new IllegalArgumentException()).when(mockObject).someVoidMethod();
//		 
//		//does nothing the first time:
//		mockObject.someVoidMethod();
//		 
//		//throws IllegalArgumentException the next time:
//		mockObject.someVoidMethod();
	}
}
