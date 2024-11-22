package com.sec.demo.jwt;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class Helper {

	private String key = "xyz43333333333333333333333333333333333333333333333333333333333333333333333333333333";
	private static final long expDate = 864_000_000;

	public String generateTokenByUserName(String username) {
		System.out.println(new Date(System.currentTimeMillis() + expDate));

		String token = Jwts.builder().setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expDate * 1000))
				.signWith(SignatureAlgorithm.HS256, key).compact();

//						.setExpiration(new Date(System.currentTimeMillis()+expDate))

		return token;
	}

	public String getUsernameByToken(String token) {

		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();

	}

}
