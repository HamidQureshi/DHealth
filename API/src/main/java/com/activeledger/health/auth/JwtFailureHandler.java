package com.activeledger.health.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.activeledger.health.model.Resp;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JwtFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		ObjectMapper mapper=new ObjectMapper();
		Resp resp=new Resp();
    	resp.setCode(401);
    	response.setContentType("application/json");
    	resp.setDesc("User not found");
    	response.setStatus(response.SC_UNAUTHORIZED);
    	response.getWriter().write(mapper.writeValueAsString(resp));
	}

}
