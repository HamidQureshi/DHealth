package com.activeledger.health.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.activeledger.health.model.ActiveResponse;
import com.activeledger.health.model.Resp;
import com.activeledger.health.model.User;
import com.activeledger.health.service.ActiveService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authManager;
	private ActiveService activeService;
	ObjectMapper mapper;
	private String secret;

	public UserAuthenticationFilter(AuthenticationManager authenticationManager, ActiveService activeService, String secret) {
		// TODO Auto-generated constructor stub
		this.authManager = authenticationManager;
		this.activeService = activeService;
		mapper = new ObjectMapper();
		this.secret=secret;

	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException{

		
		String username=null;
		String password=null;
			final String authorization = request.getHeader("Authorization");
			 if (authorization != null && authorization.toLowerCase().startsWith("basic ")) {
			//Authorization: Basic base64credentials
				 String base64Credentials = authorization.substring("Basic".length()).trim();
				 byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
				 String credentials = new String(credDecoded, StandardCharsets.UTF_8);
			
				 final String[] values = credentials.split(":", 2);
				 
				  username = values[0];
				  password = values[1];
			 }
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password,
					Collections.emptyList());

			return authManager.authenticate(authToken);
			 

		
	}

	// Upon successful authentication, generate a token.
	// The 'auth' passed to successfulAuthentication() is the current
	// authenticated user.
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain, Authentication auth) throws IOException, ServletException {

		Long now = System.currentTimeMillis();

		Claims claims = Jwts.claims().setSubject(auth.getName());
		String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
		// Add token to header
		User user = activeService.findUserbyUsername(auth.getName());
		Resp resp = new Resp();
		resp.setCode(200);
		resp.setDesc("Successfully logged in");
		ActiveResponse activeResp=new ActiveResponse();
		activeResp.setResp(resp);
		Map<String,String> mapResp;
			if (user.getIdentity() != null) {
				try {
				mapResp = activeService.retrieveUser(user.getIdentity());
				mapResp.put("identity", user.getIdentity());
				activeResp.setStream(mapResp);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
			System.out.println("---------activeResp------\n"+mapper.writeValueAsString(activeResp));
		response.getWriter().write(mapper.writeValueAsString(activeResp));
		response.setContentType("application/json");
		response.addHeader("Token", "Token " + token);

	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {
		Resp resp=new Resp();
    	resp.setCode(401);
    	response.setContentType("application/json");
    	resp.setDesc("Invalid username or password");
    	response.setStatus(response.SC_UNAUTHORIZED);
    	response.getWriter().write(mapper.writeValueAsString(resp));
	}
}
