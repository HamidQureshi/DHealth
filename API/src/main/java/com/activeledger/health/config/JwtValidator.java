/*package com.activeledger.health.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.stereotype.Component;

import com.activeledger.health.model.User;


@Component
public class JwtValidator {

	private String secret = "Activeledger";

    public User validate(String token) {

        User user = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            user = new User();

            user.setEmail(body.getSubject());
            
            
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return user;
    }
}
*/