package com.sec.demo.jwt;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class Helper {
	
	private String key = "Motu143333333333333333333333333333333333333333333333333333333333333333333333333333333";

	private static final long expDate = 864_000_000;

	public String generateToken(String Username) {
		
		String token = Jwts.builder().setSubject(Username)
//				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expDate * 1000))
				.signWith(SignatureAlgorithm.HS256, key).compact();

		
		return token;
				
	}
	
	
	public String getUsernameByToken(String token) {
		System.out.println(token+" get name ");
		
		String name= Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();

		System.out.println(name+" mera nam ");
		
		return name;
		
	}

}
