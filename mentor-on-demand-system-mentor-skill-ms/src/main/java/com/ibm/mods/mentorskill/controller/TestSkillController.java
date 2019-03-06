package com.ibm.mods.mentorskill.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TestSkillController {
	
	@GetMapping("/skill/test")
	public String requireNoRole() {
		return "Hello GUEST User";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/skill/test/admin")
	public String requireAdminRole() {
		return "Hello ADMIN User";
	}
	
	@PreAuthorize("hasRole('MENTOR')")
	@GetMapping("/skill/test/mentor")
	public String requireMentorRole() {
		return "Hello MENTOR User";
	}
	
	@PreAuthorize("hasAnyRole('MENTOR', 'ADMIN')")
	@GetMapping("/skill/test/all")
	public String requireAllRole() {
		return "Hello ALL users";
	}

}
