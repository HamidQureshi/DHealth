package com.activeledger.health.config;

import java.util.Collections;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.activeledger.health.dao.UserDao;
import com.activeledger.health.model.User;


@Component
public class JwtValidator {

	private String secret = "Activeledger";
	@Autowired
	UserDao userDao;

    public org.springframework.security.core.userdetails.User validate(String token) {

        User user = null;
        try {
        	
        	System.out.println("Token in validator: "+token);
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            user = userDao.findByUsername(body.getSubject());
            if (user == null) {
                throw new UsernameNotFoundException(body.getSubject());
            }
    		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.EMPTY_LIST);
    	}

        catch (Exception e) {
           throw new RuntimeException(e);
        }

   
    }
}
