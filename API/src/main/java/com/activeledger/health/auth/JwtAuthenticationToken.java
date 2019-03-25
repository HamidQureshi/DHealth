package com.activeledger.health.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;


public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken{


	private static final long serialVersionUID = 8236375340914110342L;
	private String token;
	
	
	public JwtAuthenticationToken(String token) {
		super(null, null);
		this.setToken(token);
		// TODO Auto-generated constructor stub
	}


	public String getToken() {
		return token;
	}


	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}


	public void setToken(String token) {
		this.token = token;
	}

}
