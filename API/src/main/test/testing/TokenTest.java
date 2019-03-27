package testing;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:health.properties")
public class TokenTest {

	@Value("${token.secret}")
	private String secret;
	@Test
	public void createToker()
	{	
		System.out.println("---"+secret);
		Claims claims = Jwts.claims()
                .setSubject("nomi");
		String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
	//	String nomi=Jwts.parser().setSigningKey("Activeledger").parse(token).getBody().;
		System.out.println("---");
		String test=Jwts.parser()
        .setSigningKey(secret)
        .parseClaimsJws("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJub21pIn0.q71jcT6OwHZw4kZYtapf_KthF3rgSzQveIT-GdlwYXdT7YgJaH8DSjlzd4nsl2G8H9rLax3hz11TB-lLioeapg")
        .getBody().getSubject();
		System.out.println("---"+test);
	}
}
