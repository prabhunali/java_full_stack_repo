package com.ibm.mods.user.security.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class AuthToken {

    private String token;
    private String type = "Bearer";
    private String username;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean active;
    private boolean verified;
    private long id;
    
	public AuthToken(){

    }

    public AuthToken(String token){
        this.token = token;
    }
    
    public AuthToken(String token
    			   , String username
    			   , Collection<? extends GrantedAuthority> authorities
    			   , boolean active
    			   , boolean verified
    			   , long id){
        this.token = token;
        this.username = username;
        this.authorities = authorities;
        this.active = active;
        this.verified = verified;
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	

}
