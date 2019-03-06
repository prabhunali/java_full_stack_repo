package com.ibm.mods.user.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ibm.mods.user.model.Role;
import com.ibm.mods.user.model.SignupUser;
import com.ibm.mods.user.model.User;
import com.ibm.mods.user.repository.RoleRepository;
import com.ibm.mods.user.repository.UserRepository;
import com.ibm.mods.user.service.UserService;
import com.ibm.mods.user.service.impl.UserServiceImpl;
import com.ibm.mods.user.util.EmailSender;

@RunWith(SpringRunner.class)
public class UserServiceImplUnitTest {
	
	@TestConfiguration
    static class UserServiceImplTestContextConfiguration {
  
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }
	
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private RoleRepository roleRepository;
	
	@Autowired
	private EmailSender emailSender;
	
	@Test
	public void signupTestNotNull() throws Exception {
//		SignupUser signupUser = new SignupUser();
//		signupUser.setId(1);
//		signupUser.setUsername("username");
//		signupUser.setPassword("password");
//		signupUser.setFirstName("fname");
//		signupUser.setLastName("lname");
//		signupUser.setContactNumber("1");
//		signupUser.setRegistrationCode("xxx123");
//		signupUser.setRoleName("USER");
//		
//		User newUser = new User();
//		newUser.setId(signupUser.getId());
//		newUser.setUsername(signupUser.getUsername());
//		newUser.setPassword(signupUser.getPassword());
//		newUser.setFirstName(signupUser.getFirstName());
//		newUser.setLastName(signupUser.getLastName());
//		newUser.setContactNumber(signupUser.getContactNumber());
//		newUser.setRegistrationDateTime(new Date());
//		newUser.setRegistrationCode("xxx123");
//		newUser.setActive(false);
//		newUser.setVerified(false);
//		
//		Role role = new Role();
//		role.setId(1);
//		role.setName("USER");
//		role.setDescription("USER ROLE");
//		
//		newUser.setRoles(new HashSet<Role>() {
//			private static final long serialVersionUID = 1L;
//		{
//			add(role);
//		}});
//		
//		
//		Mockito.when(userRepository.save(newUser)).thenReturn(newUser);
//	
//		User actual = userService.signup(signupUser);
//		assertThat(actual.getId()).isEqualTo(newUser.getId());
//		
	}
	
	@Test
	public void signupTestThrowsException() {
		
	}
	
	@Test
	public void emailExistsTest() {
		
	}
	
	@Test
	public void confirmUserEmailTest() {
		
	}
	
	@Test
	public void findAllTest() {
		
	}
	
	@Test
	public void blockUserTest() {
		
	}
	
	@Test
	public void findByUsernameTest() {
		
	}
	
}
