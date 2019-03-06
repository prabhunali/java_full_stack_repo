package com.ibm.mods.admin.service.impl;

import static com.ibm.mods.admin.security.model.Constants.HEADER_STRING;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ibm.mods.admin.model.Skill;
import com.ibm.mods.admin.service.AdminService;

@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {
	
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
	public List<Skill> getSkills(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Skill> getSkills() {
		
		return null;
	}

	@Override
	public Skill getSkill(long skillId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Skill addSkill(Skill skill) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Skill editSkill(Skill skill) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteSkill(long id) {
		// TODO Auto-generated method stub
		
	}

}
