package com.ibm.mods.mentorskill.service.impl;

import static com.ibm.mods.mentorskill.security.config.Constants.HEADER_STRING;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ibm.mods.mentorskill.repository.UserRepository;
import com.ibm.mods.mentorskill.security.model.User;
import com.ibm.mods.mentorskill.service.AuthenticationService;

@Service(value = "userService")
public class AuthenticationServiceImpl implements AuthenticationService {
	
	//@Autowired
	//private RestTemplate restTemplate;
	
	@Autowired
	private UserRepository userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//RestTemplate restTemplate = new RestTemplate();
		//User user = restTemplate.getForObject("http://localhost:8082/user-api/users/" + username, User.class);
		
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
	
	/**
	 *Create request and get response from API
	 * @param url
	 * @param responseType
	 * @return
	 */
	public <T> T getResponse(HttpServletRequest request, URI url, Class<T> responseType) {
		final String authorizationValue = request.getHeader("Authorization");
		//HttpHeaders headers = new HttpHeaders();
    	//headers.set("Authorization", authorizationValue);
    	//HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		RestTemplate restTemplate = new RestTemplate();
		RequestEntity<?> requestEntity = RequestEntity.get(url)
								  .header(HEADER_STRING, authorizationValue)
								  .accept(MediaType.APPLICATION_JSON)
								  .build();
		
		ResponseEntity<T> exchange = restTemplate.exchange(requestEntity, responseType);
		return exchange.getBody();
	}

	@Override
	public User getUserById(long id) {
		return userDao.getOne(id);
	}

}
