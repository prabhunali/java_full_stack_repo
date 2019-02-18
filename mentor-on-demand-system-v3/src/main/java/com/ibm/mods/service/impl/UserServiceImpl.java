package com.ibm.mods.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.ibm.mods.mentor.model.Mentor;
import com.ibm.mods.repository.MentorRepository;
import com.ibm.mods.repository.RoleRepository;
import com.ibm.mods.repository.UserRepository;
import com.ibm.mods.service.UserService;
import com.ibm.mods.user.auth.model.Role;
import com.ibm.mods.user.model.User;
import com.ibm.mods.user.model.UserProfile;
import com.ibm.mods.user.model.SignupUser;
import com.ibm.mods.util.EmailSender;
import com.ibm.mods.util.Utils;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userDao;
	
	//@Autowired
	//private BCryptPasswordEncoder bcryptEncoder;
	
	@Autowired
	private RoleRepository roleDao;
	
	@Autowired
	private MentorRepository mentorDao;
	
//	@Bean
//    private BCryptPasswordEncoder encoder(){
//        return new BCryptPasswordEncoder();
//    }

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<User>();
		userDao.findAll().iterator().forEachRemaining(users::add);
		return users;
	}

	@Override
	public void delete(long id) {
		userDao.deleteById(id);
	}

	@Override
	public User findOne(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User findById(Long id) {
		return userDao.findById(id).get();
	}

	/*@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
	}
	
	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			//authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}*/

	// USER, ADMIN ROLE
//	@Override
//	public User confirmEmail(SignupUser userdto) {
//		User user = userDao.findByUsername(userdto.getUsername());
//		
//		if(!isEmailConfirmed(userdto.getUsername())) {
//			user.setRegistrationCode(user.getRegistrationCode().concat("-").concat(userdto.getRegistrationCode()));
//			userDao.save(user);
//		}
//		
//		return user;
//	}
	
	public boolean isEmailConfirmed(String username) {
		User user = userDao.findByUsername(username);
		String codes[] = new String[2];
		if(user != null) {
			codes = user.getRegistrationCode().split("-");
		}

		return codes[0].equals(codes[1]) && user.getRegistrationCode().contains("-");
	}

	// ADMIN ROLE
	@Override
	public User blockUser(String username) {
		User user = userDao.findByUsername(username);
		user.setActive(false);
		return null;
	}

	@Override
	public void updateUserProfile(UserProfile userProfile) {
		// Update user
		User user = findById(userProfile.getId());
		user.setFirstName(userProfile.getFirstName());
		user.setLastName(userProfile.getLastName());
		user.setContactNumber(userProfile.getContactNumber());
		userDao.save(user);
		
		// Update Mentor
		Mentor mentor = mentorDao.findById(userProfile.getId()).get();
		mentor.setLinkedinUrl(userProfile.getLinkedInUrl());
		mentor.setYearsOfExperience(userProfile.getTotalYearsExp());
		mentor.setIntroduction(userProfile.getIntroduction());
		mentorDao.save(mentor);
	}

	@Override
	public UserProfile getUserProfile(long mentorId) {
		User user = findById(mentorId);
		Mentor mentor = mentorDao.findById(mentorId).get();	
		UserProfile userProfile = new UserProfile();
		userProfile.setFirstName(user.getFirstName());
		userProfile.setLastName(user.getLastName());
		userProfile.setTotalYearsExp(mentor.getYearsOfExperience());
		userProfile.setContactNumber(user.getContactNumber());
		userProfile.setLinkedInUrl(mentor.getLinkedinUrl());
		userProfile.setIntroduction(mentor.getIntroduction());
		return userProfile;
	}
}
