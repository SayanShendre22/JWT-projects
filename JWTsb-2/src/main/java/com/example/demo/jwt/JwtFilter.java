package com.example.demo.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	
	@Autowired
	UserDetailsService uds;
	
	@Autowired
	JwtHelper jh;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		System.out.println("filter  :");
		String header = request.getHeader("Authorization");
		String username = null;
		String token = null;

		System.out.println(header);
		if (header != null && header.startsWith("sayan")) {
			
			try {
				token = header.substring(6);
				username= jh.getUsernameFromToken(token);
				System.out.println(token+" : "+username);
			}
			catch (Exception e) {
				System.out.println("shit bhai exxeption " + e);
			}

		}
		else {
			System.out.println("Invalid Header Value");
		}
		
		
		//fetch user detail from username
		UserDetails userDetails = this.uds.loadUserByUsername(username);
        
		    //set the authentication
		    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		    SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		
		 filterChain.doFilter(request, response);
	}

}
