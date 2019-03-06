package com.ibm.mods.training.service.impl;

import static com.ibm.mods.training.security.config.Constants.HEADER_STRING;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.ibm.mods.training.model.Skill;
import com.ibm.mods.training.security.model.User;
import com.ibm.mods.training.service.ExternalApiService;

public class ExternalApiServiceImpl implements ExternalApiService {

	@Override
	public User getUser(HttpServletRequest request, long id) {
		String url = "";
		URI uri = new UriTemplate(url).expand(id);
		return getResponse(request, uri, User.class);
	}

	@Override
	public Skill getSkill(HttpServletRequest request, long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 *Create request and get response from API
	 * @param url
	 * @param responseType
	 * @return
	 */
	private <T> T getResponse(HttpServletRequest request, URI url, Class<T> responseType) {
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

}
