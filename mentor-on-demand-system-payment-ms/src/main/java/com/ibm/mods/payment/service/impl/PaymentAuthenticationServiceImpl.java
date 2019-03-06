package com.ibm.mods.payment.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ibm.mods.payment.repository.UserRepository;
import com.ibm.mods.payment.security.model.User;
import com.ibm.mods.payment.service.PaymentAuthenticationService;

@Service(value = "userService")
public class PaymentAuthenticationServiceImpl implements PaymentAuthenticationService {

	@Autowired
	UserRepository userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// NOTE: Password is json ignored, so retrieve it internally instead of calling user api
		//final String url = uriRootBuilder.userRootURL() + USER_BY_USERNAME + "/" + username;
		//User user = httpApiCrud.get(url, User.class);
		
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

}
