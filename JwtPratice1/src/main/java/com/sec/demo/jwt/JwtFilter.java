package com.sec.demo.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.sec.demo.entity.user;
import com.sec.demo.service.userService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	Helper jh;

	@Autowired
	userService us;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");
		String username = null;
		String token = null;
		System.out.println(header);

		if (header != null && header.startsWith("Bearer")) {
			token = header.substring(7);
			try {
				username = jh.getUsernameByToken(token);
			} catch (Exception e) {
				System.out.println("exe inside header if " + e);
			}

		} else {
			System.out.println("Invalid header!!!");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails ud = us.loadUserByUsername(username);

			// set authentication
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(ud, null,
					ud.getAuthorities());
			auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(auth);

		}
		
		filterChain.doFilter(request, response);

	}

}
