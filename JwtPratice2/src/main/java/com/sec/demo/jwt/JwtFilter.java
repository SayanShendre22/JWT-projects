package com.sec.demo.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sec.demo.service.userService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	Helper h ;
	
	@Autowired
	userService us;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String header = request.getHeader("Authorization");
		String username = null;
		String token = null;
		
		System.out.println(header);
		
		if(header!=null && header.startsWith("Bearer")) {
			
			token = header.substring(7);
			System.out.println(token);
			try {
				username = h.getUsernameByToken(token);
				System.out.println(username);
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println("Invalid token");
				e.printStackTrace();
			}
			
			
			
			if(username!=null &&  SecurityContextHolder.getContext().getAuthentication()==null) {
				//get userDetails
				
				UserDetails ud = us.loadUserByUsername(username);
				
				//set auth
					
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(ud, null,ud.getAuthorities());
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
			
			
		}
		
		
		
		filterChain.doFilter(request, response);
		
	}

}
