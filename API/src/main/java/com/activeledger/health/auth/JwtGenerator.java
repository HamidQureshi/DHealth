package com.activeledger.health.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.activeledger.health.model.User;


@Component
public class JwtGenerator {

	@Value("${token.secret}")
	private String secret;
	public String generate(User user) {


        Claims claims = Jwts.claims()
                .setSubject(user.getUsername());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
