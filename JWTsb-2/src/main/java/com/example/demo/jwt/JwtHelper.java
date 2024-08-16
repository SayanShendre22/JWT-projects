package com.example.demo.jwt;

import java.sql.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtHelper {

	 private static final String SECRET = "motupotuchotumotupotuagainchotumotupotuchotumotupotuagainchotumotupotuchotumotupotuagainchotu";
	 private static final long EXPIRATION_TIME = 864_000_000; // 10 days
	    
	
	public String generateJwtToken(String username) {
		String token = Jwts.builder()
						.setSubject(username)
						.setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
						.signWith(SignatureAlgorithm.HS256,SECRET)
						.compact();
		
		return token;
	}
	
	
	public String getUsernameFromToken(String token) {
		
		Jws<Claims> claim = Jwts.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(token);
		
		return claim.getBody().getSubject();
		
	}
	
	
}
