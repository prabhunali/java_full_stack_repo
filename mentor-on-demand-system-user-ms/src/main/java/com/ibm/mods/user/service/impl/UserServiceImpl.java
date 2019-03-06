package com.ibm.mods.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ibm.mods.user.model.Role;
import com.ibm.mods.user.model.SignupUser;
import com.ibm.mods.user.model.User;
import com.ibm.mods.user.repository.RoleRepository;
import com.ibm.mods.user.repository.UserRepository;
import com.ibm.mods.user.service.UserService;
import com.ibm.mods.user.util.EmailSender;
import com.ibm.mods.user.util.URIRootBuilder;
import com.ibm.mods.user.util.URL;
import com.ibm.mods.user.util.Utils;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserRepository userDao;
	
	@Autowired
	private RoleRepository roleDao;
	
	@Autowired
	private EmailSender emailSender;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Autowired
	private URIRootBuilder uriRootBuilder;
	
	@Override
	public User findOne(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User findById(Long id) {
		return userDao.findById(id).get();
	}

	@Override
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
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
		
	}

	@Override
	public User signup(SignupUser user) throws Exception {
		
		User newUser = new User();
		String confirmationCode = Utils.generateRegistrationCode();
		
		if(emailExists(user.getUsername())) {
			throw new Exception("Email already exists.");
		} else {
			newUser.setUsername(user.getUsername());
			newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
			newUser.setFirstName(user.getFirstName());
			newUser.setLastName(user.getLastName());
			newUser.setContactNumber(user.getContactNumber());
			newUser.setRegistrationDateTime(new Date());
			newUser.setRegistrationCode(confirmationCode);
			newUser.setActive(false);
			newUser.setVerified(false);
			Role role = roleDao.findByName(user.getRoleName());
			newUser.setRoles(new HashSet<Role>() {
				private static final long serialVersionUID = 1L;
			{
				add(role);
			}});
			userDao.save(newUser);
			
			// Send Registration Code to user's email for verification purpose
			sendRegistrationCodeEmail(user.getUsername(), confirmationCode);
		}
 		
		return newUser;
	}
	
	@Override
	public boolean emailExists(String email) {
		try {
    		if(findOne(email).equals(null) || findOne(email) == null) {
    			return false;
    		}
    	} catch(NullPointerException ex) {
    		return false;
    	}
    	
    	return true;
	}
	
	@Override
	public void sendRegistrationCodeEmail(String email, String code) throws Exception {
		try {
			// http://localhost:8082/verify_email
			String uri = uriRootBuilder.userRootURL() + URL.USER_VERIFY_EMAIL_2 + "/" + email + "/" + code;
			
			StringBuilder message = new StringBuilder();
			message.append("Please confirm this email by clicking below link:\n")
				   .append("     " + uri)
				   .append("")
				   .append("\n")
				   .append("Thank you!");
			
			emailSender.sendSimpleEmail(email, "Mentor on Demand System Email Verification", message.toString());
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}	
		
	}

	@Override
	public String confirmUserEmail(String email, String token) {
		User user = userDao.findByUsernameAndToken(email, token);
		if(!user.equals(null)) {
			user.setRegistrationCode(token);
			user.setVerified(true);
			user.setActive(true);
			userDao.save(user);
			return "Congratulations! You have successfully confirmed your email.";
		}
		
		return "Failed to confirm your email.";
	}
	
	public boolean isEmailConfirmed(String username) {
		User user = userDao.findByUsername(username);
		String codes[] = new String[2];
		if(user != null) {
			codes = user.getRegistrationCode().split("-");
		}

		return codes[0].equals(codes[1]) && user.getRegistrationCode().contains("-");
	}
	
	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<User>();
		userDao.findAll().iterator().forEachRemaining(users::add);
		return users;
	}
	
	// ADMIN ROLE
	@Override
	public User blockUser(long userId, boolean block) {
		User user = userDao.findById(userId).get();
		user.setActive(!block);
		return userDao.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

}
