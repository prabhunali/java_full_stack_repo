package com.ibm.mods.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.ibm.mods.user.model.LoginUser;
import com.ibm.mods.user.model.SignupUser;
import com.ibm.mods.user.model.User;
import com.ibm.mods.user.security.config.TokenProvider;
import com.ibm.mods.user.security.model.AuthToken;
import com.ibm.mods.user.service.UserService;
import com.ibm.mods.user.util.URL;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;
    
    /**
     * @param loginUser
     * @return
     * @throws AuthenticationException
     * Sample URL: http://localhost:8083/users/generate-token
     */
    @RequestMapping(value = URL.AUTH_SIGNIN, method = RequestMethod.POST)
    public ResponseEntity<?> login(HttpServletRequest request, @RequestBody LoginUser loginUser) throws AuthenticationException {
    	
    	User existingUser = userService.findOne(loginUser.getUsername());
    	
    	if(!existingUser.isVerified()) {
    		throw new AuthenticationCredentialsNotFoundException("Please verify your email to continue signing in.");
    	}
    	
    	if(!existingUser.isActive()) {
    		throw new AuthenticationCredentialsNotFoundException("User is not allowed to login.");
    	}
    	
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		loginUser.getUsername(),
                		loginUser.getPassword()
                )
        );
        
        UserDetails user = (UserDetails) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        //return ResponseEntity.ok(new AuthToken(token));
        return ResponseEntity.ok(new AuthToken(token
        									 , user.getUsername()
        									 , user.getAuthorities()
        									 , existingUser.isActive()
        									 , existingUser.isVerified()
        									 , existingUser.getId()));
    }
    
    @RequestMapping(value=URL.AUTH_SIGNUP, method = RequestMethod.POST)
    public User signup(@RequestBody SignupUser user) throws Exception{
        return userService.signup(user);
    }
    
    @GetMapping(URL.USER_VERIFY_EMAIL)
    public String confirmUserEmail(@PathVariable String email, @PathVariable String token) {
    	return userService.confirmUserEmail(email, token);
    }
    
    @GetMapping("/sendmail")
    public void sendRegistrationCodeEmail() throws Exception {
    	userService.sendRegistrationCodeEmail("jekjektorres_agustin@yahoo.com", "xxxxxx");
    }
    
	/*
	 * @PreAuthorize("hasAnyRole('USER', 'MENTOR', 'ADMIN')")
	 * 
	 * @RequestMapping(value = URL.USER_GET_BY_USERNAME, method = RequestMethod.GET)
	 * public User getUserByUsername(@PathVariable String username){ return
	 * userService.findByUsername(username); }
	 */

}
