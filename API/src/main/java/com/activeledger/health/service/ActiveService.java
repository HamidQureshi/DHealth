package com.activeledger.health.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.activeledger.java.sdk.signature.Sign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.activeledger.health.adapter.ActiveledgerAdapter;
import com.activeledger.health.dao.UserDao;
import com.activeledger.health.model.User;
import com.activeledger.health.utility.JsonParsing;

@Component
public class ActiveService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private ActiveledgerAdapter activeledgerAdapter;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private JsonParsing parsing;
	private Map<String, String> respMap;
	Sign sign;

	public ActiveService() {
		parsing = new JsonParsing();
		respMap = new HashMap<>();

	}

	public Map<String, String> sendTransaction(String token, Map<String, Object> reqTransaction) throws ParseException,
			Exception {
		// TODO Auto-generated method stub

		respMap = parsing.parseJson(activeledgerAdapter.sendTransaction(reqTransaction));
		Map<String, Object> contract = (Map) reqTransaction.get("$tx");
		if (contract.get("$contract").toString().equalsIgnoreCase("onboard")) {
			if (respMap.get("id") != null) {
				Claims body = Jwts.parser().setSigningKey("Activeledger").parseClaimsJws(token).getBody();
				User user = userDao.findByUsername(body.getSubject());
				user.setIdentity(respMap.get("id"));
				userDao.save(user);
			}

		}
		return respMap;
	}

	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userDao.save(user);
	}

	public User findUserbyUsername(String username) {
		try {
			System.out.print("user dao---" + userDao);
			return userDao.findByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---------------------------" + e.getMessage());
		}
		return null;
	}

	public String retrieveUser(String identity) {
		try {
			return activeledgerAdapter.retrieveUser(identity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
