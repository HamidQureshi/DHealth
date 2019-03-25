package com.activeledger.health.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.activeledger.java.sdk.signature.Sign;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.activeledger.health.adapter.ActiveAdapter;
import com.activeledger.health.controller.ActiveController;
import com.activeledger.health.dao.UserDao;
import com.activeledger.health.model.User;
import com.activeledger.health.utility.JsonParsing;

@Component
public class ActiveService {
	final static Logger logger = Logger.getLogger(ActiveService.class);
	@Autowired
	private UserDao userDao;
	@Autowired
	private ActiveAdapter activeledgerAdapter;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private JsonParsing parsing;
	private Map<String, String> respMap;
	Sign sign;
	@Value("${token.secret}")
	private String secret;


	public Map<String, String> sendTransaction(String token, Map<String, Object> reqTransaction) throws ParseException,
			Exception {
		JSONObject json = activeledgerAdapter.sendTransaction(reqTransaction);
		parsing = new JsonParsing();
		respMap = new HashMap<>();
		Map<String,String> map = new HashMap<>();
		Map<String,String> userDetails = null;
		logger.info("-------parsing returned json to find identity------");
		
		respMap = parsing.parseJson(json);
		
		if (respMap.get("id") != null) {
			map.put("id",respMap.get("id"));
		}
		else
		{
			map.put("error",respMap.get("error"));
		}

		return map;
	}

	public void save(User user) {
		logger.info("-------hashing password------");
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userDao.save(user);
	}

	public User findUserbyUsername(String username) {
		return userDao.findByUsername(username);
	}

	public Map<String,String> retrieveUser(String identity) throws Exception {
		parsing = new JsonParsing();	
		respMap = new HashMap<>();
		respMap = parsing.parseJson(activeledgerAdapter.retrieveUser(identity));
		return respMap;
	}

	public Map<String,String>  register(String token, Map<String, Object> transaction) throws Exception {
		
		parsing = new JsonParsing();
		respMap = new HashMap<>();
		
		Map<String,String> userDetails = new HashMap<>();
		JSONObject json = activeledgerAdapter.sendTransaction(transaction);
		logger.info("-------parsing returned json to find identity------");
		
		respMap = parsing.parseJson(json);
		
		if (respMap.get("id") != null) {
			token=token.replace("Token ", "");
			Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
			User user = userDao.findByUsername(body.getSubject());
			user.setIdentity(respMap.get("id"));
			logger.info("-------saving user with identity------");
			userDao.save(user);
			logger.info("-------retireiving user with identity------");
			userDetails = retrieveUser(respMap.get("id"));
			userDetails.put("identity",respMap.get("id") );
			return  userDetails;
		}
		
		userDetails.put("error",respMap.get("error"));
		return userDetails;
		
		
	}

}
