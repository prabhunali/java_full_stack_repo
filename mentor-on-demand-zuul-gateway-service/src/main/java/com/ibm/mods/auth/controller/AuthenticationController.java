package com.ibm.mods.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.ibm.mods.auth.service.UserService;
import com.ibm.mods.auth.config.TokenProvider;
import com.ibm.mods.auth.model.AuthToken;
import com.ibm.mods.auth.model.LoginUser;
import com.ibm.mods.auth.model.SignupUser;
import com.ibm.mods.auth.model.User;
import com.ibm.mods.auth.util.URL;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
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
     * Sample URL: http://localhost:2222/users/generate-token
     */
    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginUser loginUser) throws AuthenticationException {
    	
    	User existingUser = userService.findOne(loginUser.getUsername());
    	
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

}
