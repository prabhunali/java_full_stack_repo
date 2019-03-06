package com.ibm.mods.mentorskill.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ibm.mods.mentorskill.security.config.Constants;

@Component
public class HttpApiCrud {
	
	private HttpServletRequest httpRequest;
	
	@Autowired
	public HttpApiCrud(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}
	
	private HttpHeaders getHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		final String authorizationValue = this.httpRequest.getHeader(Constants.HEADER_STRING);
		headers.set(Constants.HEADER_STRING, authorizationValue);
		return headers;
	}
	
	public <T> T create(String url, T requestType, Class<T> responseType) {
		HttpEntity<T> requestEntity = new HttpEntity<T>(requestType, getHttpHeaders());
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseType);
		return response.getBody();
	}
	
	public <T> T get(String url, Class<T> responseType) {
		HttpEntity<T> entity = new HttpEntity<T>(getHttpHeaders());
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<T> response = restTemplate.exchange(
    		    url, HttpMethod.GET, entity, responseType);
    	return response.getBody();
	}
	
	/**
	 * @param url
	 * @return
	 */
	public <T> List<T> getList(String url) {
		HttpEntity<T> entity = new HttpEntity<T>(getHttpHeaders());
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<T>> response = restTemplate.exchange
				(
				  url,
				  HttpMethod.GET,
				  entity,
				  new ParameterizedTypeReference<List<T>>(){}
				);
		
		return response.getBody();
	}
	
	/**
	 * @param url
	 * @param requestType
	 * @return
	 */
	public <T> boolean update(String url, T requestType) {
		HttpEntity<T> requestEntity = null;
		if(requestType == null) {
			requestEntity = new HttpEntity<T>(getHttpHeaders());
		} else {
			requestEntity = new HttpEntity<T>(requestType, getHttpHeaders());
		}
		
		ResponseEntity<Object> response = null;
		try {
			RestTemplate restTemplate = new RestTemplate();		
			response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Object.class);
			HttpStatus status = response.getStatusCode();
			return (status == HttpStatus.OK) ? true : false;
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @param url
	 * @param requestType
	 * @return
	 */
	public <T> boolean delete(String url, T requestType) {
		HttpEntity<T> requestEntity = null;
		if(requestType == null) {
			requestEntity = new HttpEntity<T>(getHttpHeaders());
		} else {
			requestEntity = new HttpEntity<T>(requestType, getHttpHeaders());
		}
		
		ResponseEntity<Object> response = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Object.class);
			HttpStatus status = response.getStatusCode();
			return (status == HttpStatus.OK) ? true : false;
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}


}
