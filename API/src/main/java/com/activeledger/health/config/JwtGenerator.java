/*package com.activeledger.health.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.stereotype.Component;

import com.activeledger.health.model.User;


@Component
public class JwtGenerator {
	public String generate(User user) {


        Claims claims = Jwts.claims()
                .setSubject(user.getEmail());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", jwtUser.getRole());


        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "Activeledger")
                .compact();
    }
}
*/