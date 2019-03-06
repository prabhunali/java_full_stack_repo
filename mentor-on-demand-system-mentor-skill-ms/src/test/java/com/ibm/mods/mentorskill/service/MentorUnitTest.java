package com.ibm.mods.mentorskill.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.base.Optional;
import com.ibm.mods.mentorskill.model.Mentor;
import com.ibm.mods.mentorskill.model.MentorDto;
import com.ibm.mods.mentorskill.repository.MentorRepository;
import com.ibm.mods.mentorskill.service.impl.MentorServiceImpl;

@RunWith(SpringRunner.class)
public class MentorUnitTest {
	
	@TestConfiguration
    static class MentorServiceImplTestContextConfiguration {
  
        @Bean
        public MentorService mentorService() {
            return new MentorServiceImpl();
        }
    }
	
	@Autowired
	private MentorService mentorService;
	
	@MockBean
	private MentorRepository mentorDao;
	
	@Test
	public void saveTestReturnNotNull() {
		Mentor expected = new Mentor();
		expected.setId(1);
		expected.setUserId(10L);
		expected.setUsername("username");
		expected.setLinkedinUrl("linkedin url");
		expected.setYearsOfExperience(10);
		expected.setIntroduction("intro");
		Mockito.when(mentorDao.save(expected)).thenReturn(expected);
		
		MentorDto mentorDto = new MentorDto();
		mentorDto.setId(1);
		mentorDto.setUserId(10L);
		mentorDto.setUsername("username");
		mentorDto.setLinkedinUrl("linkedin url");
		mentorDto.setYearsOfExperience(10);
		mentorDto.setIntroduction("intro");
		Mentor actual = mentorService.save(mentorDto);
		//assertNotNull(actual);
		
		System.out.println("####actual id: " + mentorDto.getUserId());
		System.out.println("####expected id: " + expected.getUserId());
		
		//assertEquals(actual.getId(), expected.getId());
	}
	
	public void findOneByUsernameReturnNotNull() {
		Mentor expected = new Mentor();
		expected.setId(1);
		expected.setUserId(10L);
		expected.setUsername("username");
		expected.setLinkedinUrl("linkedin url");
		expected.setYearsOfExperience(10);
		expected.setIntroduction("intro");
		Mockito.when(mentorDao.findByUsername("username")).thenReturn(expected);
		
		Mentor actual = mentorService.findOne("username");
		assertEquals(expected.getUsername(), actual.getUsername());
	}
	
	public void findByIdReturnNotNull() {
//		Mentor expected = new Mentor();
//		expected.setId(1);
//		expected.setUserId(10L);
//		expected.setUsername("username");
//		expected.setLinkedinUrl("linkedin url");
//		expected.setYearsOfExperience(10);
//		expected.setIntroduction("intro");
//		Mockito.when(mentorDao.findById(1L)).thenReturn(Optional.of(expected));
//		
//		Mentor actual = mentorService.findOne("username");
//		assertEquals(expected.getUsername(), actual.getUsername());
	}
	
	public void findByIdReturnNull() {
		
	}
	
	public void updateReturnNotNull() {
		
	}
	
	public void findByUserIdReturnNotNull() {
		
	}
	
	public void findByUserIdReturnNull() {
		
	}

}
