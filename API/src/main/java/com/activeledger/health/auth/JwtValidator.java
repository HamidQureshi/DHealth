package com.activeledger.health.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.activeledger.health.dao.UserDao;
import com.activeledger.health.model.User;


@Component
public class JwtValidator {
	@Value("${token.secret}")
	private String secret;
	@Autowired
	UserDao userDao;

    public org.springframework.security.core.userdetails.User validate(String token) {

        User user = null;
        Claims body=null;

           try{  body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
          
            user = userDao.findByUsername(body.getSubject());
           }
           catch(Exception e)
           {
        	   user=null;
           }
            if (user == null) {
            	
                throw new UsernameNotFoundException(" User not found");
            }
    		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.EMPTY_LIST);

    }
}
