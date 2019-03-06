package com.ibm.mods.admin.controller;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ibm.mods.admin.util.HttpApiCrud;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TestAdminController {
	
	@Autowired
	private HttpApiCrud apiCrud;
	
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/message")
    public String getMessageFromOtherService() {
    	RestTemplate restTemplate = new RestTemplate();
    	return restTemplate.getForObject("http://localhost:8082/zuul-api/message", String.class);
    }
    
    @GetMapping("/admin/test") // http://localhost:8082/admin-api/admin/test
    public String requireNoRole() {
		
		  RestTemplate restTemplate = new RestTemplate(); return
		  restTemplate.getForObject("http://localhost:8082/mentorskill-api/skill/test",
		  String.class);
    }
    
    @GetMapping("/admin/test/admin") // http://localhost:8082/admin-api/admin/test/admin
    public String requireAdminRole() {
		/*
		 * RestTemplate restTemplate = new RestTemplate(); return
		 * restTemplate.getForObject("http://localhost:8082/skill-api/skill/test/admin",
		 * String.class);
		 */
    	
    	return apiCrud.get("http://localhost:8082/mentorskill-api/skill/test/admin", String.class);
    }
    
    @GetMapping("/admin/test/mentor") // http://localhost:8082/admin-api/admin/test/mentor
    public String requireMentorRole(HttpServletRequest request) {
    	 String url = "http://localhost:8082/mentorskill-api/skill/test/mentor";
		/*
		 * final String authorizationValue = request.getHeader("Authorization");
		 * HttpHeaders headers = new HttpHeaders(); headers.set("Authorization",
		 * authorizationValue); HttpEntity<String> entity = new
		 * HttpEntity<String>(headers); RestTemplate restTemplate = new RestTemplate();
		 * ResponseEntity<String> response = restTemplate.exchange( url, HttpMethod.GET,
		 * entity, String.class); return response.getBody();
		 */
    	
    	return apiCrud.get(url, String.class);
    }
    
    @GetMapping("/admin/test/all") // http://localhost:8082/admin-api/admin/test/all
    public String requireAllRole(HttpServletRequest request) {
    	String url = "http://localhost:8082/mentorskill-api/skill/test/all";
		/*
		  final String authorizationValue = request.getHeader("Authorization"); HttpHeaders headers
		 * = new HttpHeaders(); headers.set("Authorization", authorizationValue);
		 * HttpEntity<String> entity = new HttpEntity<String>(headers); RestTemplate
		 * restTemplate = new RestTemplate(); ResponseEntity<String> response =
		 * restTemplate.exchange( url, HttpMethod.GET, entity, String.class); return
		 * response.getBody();
		 */
    	return apiCrud.get(url, String.class);
    }

}
