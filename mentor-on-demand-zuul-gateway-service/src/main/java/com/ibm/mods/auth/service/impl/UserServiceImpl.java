package com.ibm.mods.auth.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ibm.mods.auth.model.Role;
import com.ibm.mods.auth.model.SignupUser;
import com.ibm.mods.auth.model.User;
import com.ibm.mods.auth.repository.RoleRepository;
import com.ibm.mods.auth.repository.UserRepository;
import com.ibm.mods.auth.service.UserService;
import com.ibm.mods.auth.util.EmailSender;
import com.ibm.mods.auth.util.Utils;

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
			//authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
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
			Role role = roleDao.getRoleByName(user.getRoleName());
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
			StringBuilder message = new StringBuilder();
			message.append("Please confirm this email address by entering below code.")
					.append("\n")
					.append("This is your confirmation code: ")
					.append(code);
			emailSender.sendSimpleEmail(email, "Mentor on Demand System Account Verification", message.toString());
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}	
		
	}

}
