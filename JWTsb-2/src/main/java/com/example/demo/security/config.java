package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.jwt.JwtFilter;
import com.example.demo.service.AuthService;

@Configuration
public class config {

	@Autowired
	JwtFilter jf;
	
	@Autowired
	AuthService AS;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable()
		.authorizeRequests()
		.requestMatchers("/public","/Login")
		.permitAll()
		.anyRequest()
		.authenticated();
		
		http.addFilterBefore(jf, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	
	
	public void AMB(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(AS);
	}
	
	
	
	
}
