package com.ibm.mods.admin.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.mods.admin.model.Skill;
import com.ibm.mods.admin.security.config.ApiResponse;
import com.ibm.mods.admin.security.model.URIRootBuilder;
import com.ibm.mods.admin.security.model.User;
import com.ibm.mods.admin.util.HttpApiCrud;

import static com.ibm.mods.admin.util.URL.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AdminController {
	
	@Autowired
	private HttpApiCrud apiCrud;
	
	@Autowired
	private URIRootBuilder uriBuilder;
	
	/**Skill APIs**/
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(ADMIN_GET_SKILLS)
	public List<Skill> getSkills() {
		final String url = uriBuilder.mentorSkillRootURL() + SKILLS;
		Skill[] skills = apiCrud.get(url, Skill[].class);
		return Arrays.asList(skills);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(ADMIN + SKILL_ADD)
	public Skill addSkill(@RequestBody Skill skill) {
		final String url = uriBuilder.mentorSkillRootURL() + SKILL_ADD;
		return apiCrud.create(url, skill, Skill.class);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(ADMIN + SKILL_EDIT)
	public ApiResponse<Void> editSkill(@RequestBody Skill skill) {
		final String url = uriBuilder.mentorSkillRootURL() + SKILL_EDIT;
		boolean updated = apiCrud.update(url, skill);
		String message = updated ? "Successfully updated!" : "Failed to update record!";
		return new ApiResponse<Void>(HttpStatus.OK.value(), message, null);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(ADMIN_SKILL_DELETE)
	public ApiResponse<Void> deleteSkill(@PathVariable long skillId) {
		final String url = uriBuilder.mentorSkillRootURL() + SKILL_DELETE + "/" + skillId;
		boolean deleted = apiCrud.delete(url, null);
		String message = deleted ? "Successfully deleted!" : "Failed to delete record!";
		int status = deleted ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value();
    	return new ApiResponse<Void>(status, message, null);
	}
	
	/**User APIs**/
	@PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value=ADMIN_GET_USERS, method = RequestMethod.GET)
    public List<User> listUsers(){
		final String url = uriBuilder.userRootURL() + USERS;
		User[] users = apiCrud.get(url, User[].class);
		return Arrays.asList(users);
    }
    
	/**
	 * @param userId
	 * @param block
	 * @return ApiResponse<Void>
	 */
	@PreAuthorize("hasRole('ADMIN')")
    @PutMapping(ADMIN_BLOCK_USER)
    public ApiResponse<Void> blockUser(@PathVariable Long userId, @PathVariable boolean block) {
    	final String url = uriBuilder.userRootURL() + USER_BLOCK + "/" + userId + "/" + block;
    	boolean updated =  apiCrud.update(url, null);
    	String message = updated ? "Successfully updated!" : "Failed to update record!";
    	int status = updated ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value();
    	return new ApiResponse<Void>(status, message, null);	
    }
}
